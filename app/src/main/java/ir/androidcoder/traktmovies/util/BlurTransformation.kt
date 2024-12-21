package ir.androidcoder.traktmovies.util

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.nio.charset.Charset
import java.security.MessageDigest

class BlurTransformation(private val context: Context, private val radius: Int) : BitmapTransformation() {
    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val renderScript = RenderScript.create(context)

        // ایجاد ورودی و خروجی برای بلور
        val input = Allocation.createFromBitmap(renderScript, toTransform)
        val output = Allocation.createTyped(renderScript, input.type)

        // ایجاد و تنظیم افکت بلور
        val blurScript = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
        blurScript.setRadius(radius.toFloat())  // شعاع بلور بالا
        blurScript.setInput(input)
        blurScript.forEach(output)

        // کپی کردن خروجی به یک تصویر جدید
        val blurredBitmap = Bitmap.createBitmap(toTransform.width, toTransform.height, toTransform.config)
        output.copyTo(blurredBitmap)

        renderScript.destroy()
        return blurredBitmap
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(("BlurTransformation(radius=$radius)").toByteArray(Charset.forName("UTF-8")))
    }
}