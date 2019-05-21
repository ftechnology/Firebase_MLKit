package com.faruk.mlkitsample.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.faruk.mlkitsample.R
import com.faruk.mlkitsample.activity.*
import com.faruk.mlkitsample.data.AdapterData
import java.util.ArrayList

class HomeListAdapter(private val context: Context) : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    private var mDataList: ArrayList<AdapterData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_activity, parent, false))
    }

    fun setDataList(dataList: ArrayList<AdapterData>) {
        this.mDataList = dataList
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mDataList?.get(position)
        holder.image.setImageResource(data?.getIcon()!!)
        holder.heading.setText(data?.getTitle())
        holder.description.setText(data?.getDescription())

        holder.view.setOnClickListener {
            when (position) {
                0 -> {
                    context.startActivity(Intent(context, TextRecognitionActivity::class.java))
                }

                1 -> {
                    context.startActivity(Intent(context, FaceDetectionActivity::class.java))
                }

                2 -> {
                    context.startActivity(Intent(context, BarcodeScanningActivity::class.java))
                }

                3 -> {
                    context.startActivity(Intent(context, ImageLabelingActivity::class.java))
                }

                4 -> {
                    context.startActivity(Intent(context, LandmarkRecognitionActivity::class.java))
                }

                5 -> {
                    context.startActivity(Intent(context, LanguageIdentificationActivity::class.java))
                }

                6 -> {
                    context.startActivity(Intent(context, SmartReplyActivity::class.java))
                }
            }
        }

    }

    public fun loadData() {
        mDataList = ArrayList()
        var data = AdapterData()
        data.setTitle(context.getString(R.string.text_recognition))
        data.setDescription("Recognize and extract text from images");
        data.setIcon(R.mipmap.text_recognition)
        mDataList?.add(data)

        data = AdapterData()
        data.setTitle(context.getString(R.string.face_detection))
        data.setDescription("Detect faces and facial landmarks");
        data.setIcon(R.mipmap.face_detection)
        mDataList?.add(data)

        data = AdapterData()
        data.setTitle(context.getString(R.string.barcode_scanning))
        data.setDescription("Scan and process barcodes");
        data.setIcon(R.mipmap.barcode_scanning)
        mDataList?.add(data)

        data = AdapterData()
        data.setTitle(context.getString(R.string.image_labeling))
        data.setDescription("Identify objects, locations, activities, animal species, products, and more");
        data.setIcon(R.mipmap.image_classification)
        mDataList?.add(data)

        data = AdapterData()
        data.setTitle(context.getString(R.string.landmark_recognition))
        data.setDescription("Identify popular landmarks in an image");
        data.setIcon(R.mipmap.landmark_recognition)
        mDataList?.add(data)

        data = AdapterData()
        data.setTitle(context.getString(R.string.language_identification))
        data.setDescription("Can determine the language of a string of text");
        data.setIcon(R.mipmap.language_detection)
        mDataList?.add(data)

        data = AdapterData()
        data.setTitle(context.getString(R.string.smart_reply))
        data.setDescription("Automatically generate relevant replies to messages");
        data.setIcon(R.mipmap.smart_reply)
        mDataList?.add(data)

        setDataList(this.mDataList!!)
        this.notifyDataSetChanged()

    }

    override fun getItemCount() = mDataList?.size!!

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view = itemView
        val image = itemView.findViewById<ImageView>(R.id.image_view)!!
        val heading = itemView.findViewById<TextView>(R.id.heading_text_view)!!
        val description = itemView.findViewById<TextView>(R.id.description_text_view)!!
    }
}