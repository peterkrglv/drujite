package com.example.drujite.presentation

import android.content.Context
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

fun startQRScanner(context: Context, onResult: (QRScannerResult, String?) -> Unit) {
    val scanner = GmsBarcodeScanning.getClient(context)
    scanner.startScan()
        .addOnSuccessListener { barcode ->
            onResult(QRScannerResult.Success, barcode.rawValue)
        }
        .addOnCanceledListener {
            onResult(QRScannerResult.Canceled, null)
        }
        .addOnFailureListener { e ->
            onResult(QRScannerResult.Failure, null)
        }
}

enum class QRScannerResult {
    Success,
    Canceled,
    Failure
}
