package com.ftp.keberlanjutanumkmbsc.utils

private const val Y_OFFSET = 16
private const val Y_OFFSET_ABOVE_FAB = 80

//fun Toast.showCustomToast(
//    context: Context?,
//    message: String? = "",
//    isAboveFAB: Boolean = false,
//    durations: Int = Toast.LENGTH_LONG
//) {
//    if (context == null) {
//        return
//    }
//    val formattedMessage = message
//    val inflater: LayoutInflater =
//        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//    val layout = inflater.inflate(
//        R.layout.layout_custom_toast,
//        (context as Activity).findViewById(R.id.layoutCustomToast)
//    )
//    val tvToastMessage = layout.findViewById<TextView>(R.id.tvToastMessage)
//    tvToastMessage.text =
//        formattedMessage?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
//    val offsetY = if (isAboveFAB) {
//        Y_OFFSET_ABOVE_FAB.dpToPx()
//    } else {
//        Y_OFFSET.dpToPx()
//    }
//    setGravity(Gravity.BOTTOM or Gravity.FILL_HORIZONTAL, 0, offsetY)
//    duration = durations
//    view = layout
//    show()
//}
//
//fun Activity.showCustomToast(
//    message: String? = "",
//    isAboveFAB: Boolean = false,
//    durations: Int = Toast.LENGTH_LONG
//) {
//    val formattedMessage = message
//    Toast(this).showCustomToast(this, formattedMessage, isAboveFAB, durations)
//}
//
//fun Fragment.showCustomToast(message: String? = "", isAboveFAB: Boolean = false) {
//    val formattedMessage = message
//    Toast(this.context).showCustomToast(this.context, formattedMessage, isAboveFAB)
//}
//
//fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
