package `fun`.joshi.study.barcodereader.ui.activities

import `fun`.joshi.study.barcodereader.databinding.ActivityMainBinding
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            scanCode()
        }
    }

    private fun scanCode() {
        val options = ScanOptions().apply {
            setPrompt("Volume up to flash on")
            setBeepEnabled(true)
            setOrientationLocked(true)
            captureActivity = MyCaptureActivity::class.java

        }
        barcodeReader.launch(options)
    }

    private val barcodeReader: ActivityResultLauncher<ScanOptions> = registerForActivityResult(ScanContract()) { scanResult ->
        if(scanResult.contents != null) {
            val builder = AlertDialog.Builder(this@MainActivity)
                .setTitle("Result")
                .setMessage(scanResult.contents)
                .setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
            builder.show()
        }
    }
}