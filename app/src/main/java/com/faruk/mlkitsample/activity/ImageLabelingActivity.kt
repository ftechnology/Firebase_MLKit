package com.faruk.mlkitsample.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.esafirm.imagepicker.features.ImagePicker
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions
import com.faruk.mlkitsample.R
import com.faruk.mlkitsample.ui.ImagePickerDialog
import kotlinx.android.synthetic.main.common_scan_layout.*

class ImageLabelingActivity : AppCompatActivity(),ImagePickerDialog.Listener {

    private val imageView by lazy { findViewById<ImageView>(R.id.iv_real_iamge)!! }
    private val imageViewCam by lazy { findViewById<ImageView>(R.id.iv_camera)!! }
    private val textView by lazy { findViewById<TextView>(R.id.tv_text)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_scan_layout)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imageViewCam.setOnClickListener {
            showImagePicker(false)
        }
    }


    override fun onCameraSelected() {
        ImagePicker.cameraOnly().start(this)
    }

    override fun onGallerySelected() {
        ImagePicker.create(this)
                .showCamera(false)
                .theme(R.style.CameraPickerTheme)
                .start()
    }

    private fun showImagePicker(isCancelable: Boolean) {
        if (supportFragmentManager.findFragmentByTag(ImagePickerDialog.TAG) == null) {
            val imagePickerDialog = ImagePickerDialog()
            imagePickerDialog.isCancelable = isCancelable
            imagePickerDialog.show(supportFragmentManager, ImagePickerDialog.TAG)
        }
    }


    private fun analyzeImage(image: Bitmap?) {
        if (image == null) {
            Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
            return
        }

        imageView.setImageBitmap(null)

        showProgress()

        val firebaseVisionImage = FirebaseVisionImage.fromBitmap(image)
        val options = FirebaseVisionOnDeviceImageLabelerOptions.Builder()
                .setConfidenceThreshold(0.7F)
                .build()
        val labelDetector = FirebaseVision.getInstance().getOnDeviceImageLabeler(options)
        labelDetector.processImage(firebaseVisionImage)
                .addOnSuccessListener {
                    val mutableImage = image.copy(Bitmap.Config.ARGB_8888, true)

                    labelImage(it, mutableImage)

                    imageView.setImageBitmap(mutableImage)
                    hideProgress()

                }
                .addOnFailureListener {
                    Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
                    hideProgress()
                }
    }

    private fun labelImage(labels: List<FirebaseVisionImageLabel>?, image: Bitmap?) {
        var finalString = "";
        if (labels == null || image == null) {
            Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
            return
        }

        for (label in labels) {
            finalString = finalString + "\n" + label.text;
        }
        textView.setText(finalString)
    }

    private fun showProgress() {
        findViewById<View>(R.id.bottom_sheet_button_progress).visibility = View.VISIBLE
    }

    private fun hideProgress() {
        findViewById<View>(R.id.bottom_sheet_button_progress).visibility = View.GONE
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val image = ImagePicker.getFirstImageOrNull(data)
            if (image != null) {

                var bitmap = BitmapFactory.decodeFile(image.path);
                imageViewCam.visibility = View.GONE
                analyzeImage(bitmap)

            }
        } else {
            Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
        }
    }

}
