package com.example.drujite.presentation.character_customisation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.domain.use_cases.customisation.CustomisationCategory
import com.example.domain.use_cases.customisation.GetCharacterItemsUseCase
import com.example.domain.use_cases.customisation.GetCustomisationOptionsUseCase
import com.example.domain.use_cases.customisation.SaveCharacterImageUseCase
import com.example.domain.use_cases.customisation.SaveCharacterItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CustomisationViewModel(
    private val context: Context,
    private val getCustomisationOptions: GetCustomisationOptionsUseCase,
    private val saveCharacterItemsUseCase: SaveCharacterItemsUseCase,
    private val saveCharacterImage: SaveCharacterImageUseCase,
    private val getCharacterItemsUseCase: GetCharacterItemsUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<CustomisationState>(CustomisationState.Initialization)
    val viewState: StateFlow<CustomisationState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<CustomisationAction?>(null)
    val viewAction: StateFlow<CustomisationAction?>
        get() = _viewAction

    fun obtainEvent(event: CustomisationEvent) {
        when (event) {
            is CustomisationEvent.OptionChosen -> optionChosen(event.category, event.num)
            is CustomisationEvent.ProceedClicked -> proceedClicked(event.characterId)
            is CustomisationEvent.LoadOptions -> loadOptions(event.characterId)
        }
    }

    fun clearAction() {
        _viewAction.value = null
        _viewState.value = CustomisationState.Initialization
    }

    private fun loadOptions(characterId: Int) {
        viewModelScope.launch {
            _viewState.value = CustomisationState.Loading
            val options = getCustomisationOptions.execute().sortedBy { it.id }
            val items = getCharacterItemsUseCase.execute(characterId)
            val chosenOptions =
                options.associateWith { 0 }.toMutableMap()
            Log.d("Clothing", "Characters items: $items")
            for (item in items) {
                val category = options.find { it.id == item.typeId }
                if (category != null) {
                    val index = category.items.indexOfFirst { it.id == item.id }
                    if (index != -1) {
                        chosenOptions[category] = index
                    }
                }
            }
            Log.d("Clothing", "Characters options: $options")
            Log.d("Clothing", "Chosen options: $chosenOptions")
            _viewState.value = CustomisationState.Main(
                options = options,
                chosenOptions = chosenOptions,
                characterId = characterId,
                firstCustom = items.isEmpty()
            )
        }
    }

    private fun proceedClicked(characterId: Int) {
        val state = _viewState.value
        if (state !is CustomisationState.Main) return

        _viewState.value = CustomisationState.Loading
        viewModelScope.launch {
            val bitmap = generateImage(state.chosenOptions)
            val imageBytes = bitmapToByteArray(bitmap)
            val saveItemsResult = saveCharacterItemsUseCase.execute(
                characterId,
                state.chosenOptions
            )
            val result = saveCharacterImage.execute(characterId, imageBytes)
            if (result) {
                _viewAction.value = CustomisationAction.NavigateToMain
            } else {
                _viewState.value = state
            }
        }
    }

    private fun optionChosen(category: CustomisationCategory, num: Int) {
        val state = _viewState.value
        if (state !is CustomisationState.Main) return

        val updatedChosenOptions = state.chosenOptions.toMutableMap()
        updatedChosenOptions[category] = num
        _viewState.value = state.copy(chosenOptions = updatedChosenOptions)
    }

    private suspend fun generateImage(chosenOptions: Map<CustomisationCategory, Int>): Bitmap {
        val width = 360
        val height = 510
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val imageLoader = ImageLoader.Builder(context).components {
            add(SvgDecoder.Factory())
        }.build()
        for ((category, chosenIndex) in chosenOptions) {
            val option = category.items[chosenIndex]
            Log.d("generateImage", "Loading image: ${option.imageUrl}")
            val request = ImageRequest.Builder(context)
                .data(option.imageUrl)
                .size(width, height)
                .build()
            val result = imageLoader.execute(request)
            if (result is SuccessResult) {
                val drawable = result.drawable
                drawable.setBounds(0, 0, width, height)
                drawable.draw(canvas)
                Log.d("generateImage", "Image drawn: ${option.imageUrl}")
            } else {
                Log.e("generateImage", "Failed to load image: ${option.imageUrl}")
            }
        }
        return bitmap
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = java.io.ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }


//    private fun loadOptions() {
//        viewModelScope.launch {
//            _viewState.value = CustomisationState.Loading
//            val resources = context.resources
//
//            val categories = resources.getStringArray(R.array.categories)
//            val customisationCategories = categories.mapIndexed { categoryId, categoryName ->
//                val optionsArrayId =
//                    resources.getIdentifier(categoryName, "array", context.packageName)
//                val options = if (optionsArrayId != 0) {
//                    resources.getStringArray(optionsArrayId).mapIndexed { optionId, optionName ->
//                        val resourceId =
//                            resources.getIdentifier(optionName, "drawable", context.packageName)
//                        CustomisationOption(id = optionId, resourceId = resourceId)
//                    }
//                } else {
//                    emptyList()
//                }
//                CustomisationCategory(
//                    id = categoryId,
//                    name = categoryName,
//                    options = options
//                )
//            }
//            val chosenOptions = customisationCategories.associateWith { 0 }
//
//            _viewState.value = CustomisationState.Main(
//                options = customisationCategories,
//                chosenOptions = chosenOptions
//            )
//        }
//    }

//    private fun proceedClicked(characterId: Int) {
//        val state = _viewState.value
//        if (state !is CustomisationState.Main) {
//            return
//        }
//        _viewState.value = CustomisationState.Loading
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val result = saveCustomImage.execute(characterId, state.chosenOptions)
//                if (result) {
//                    _viewAction.value = CustomisationAction.NavigateToMain
//                } else {
//                    Log.e("network", "error saving image")
//                }
//                _viewState.value = state
//            }
//        }
//    }

//    private fun proceedClicked(characterId: Int) {
//        val state = _viewState.value
//        if (state !is CustomisationState.Main) {
//            return
//        }
//        _viewState.value = CustomisationState.Loading
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val imageBytes = generateImage(state.chosenOptions, context)
//                _viewState.value = state.copy(img = imageBytes.asImageBitmap())
//            }
//        }
//    }
//
//    private fun optionChosen(category: CustomisationCategory, num: Int) {
//        val state = _viewState.value
//        if (state !is CustomisationState.Main) {
//            return
//        }
//        val updatedChosenOptions = state.chosenOptions.toMutableMap()
//        updatedChosenOptions[category] = num
//        _viewState.value = state.copy(chosenOptions = updatedChosenOptions)
//    }
}

