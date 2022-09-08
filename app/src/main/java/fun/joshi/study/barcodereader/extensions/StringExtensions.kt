package `fun`.joshi.study.barcodereader.extensions

fun String.formatBarcode(): String {
    return if (this.length == 44) {
        val p1 = this.substring(0, 11)
        val p2 = this.substring(11, 22)
        val p3 = this.substring(22, 33)
        val p4 = this.substring(33, 44)
        "$p1 $p2 $p3 $p4"
    } else {
        this
    }
}
