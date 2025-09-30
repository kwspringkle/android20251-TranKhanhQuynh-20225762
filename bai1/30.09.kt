class PhanSo(var tuso: Int, var mauso: Int){
    //Nhap vao phan so tu ban phim
    fun input(){
        do {
            print("Nhap tu so: ")
            tuso = readln().toInt()
            print("Nhap mau so: ")
            mauso = readln().toInt()
            if (tuso == 0 || mauso == 0) {
                println("Tu so hoac mau so bang 0. Vui long nhap lai")
            }
        } while (tuso == 0 || mauso == 0) 
    }
    //In phan so ra man hinh
    fun output(){
        println("$tuso/$mauso")
    }
    //Tim UCLN
    private fun ucln(a:Int, b:Int): Int{
        return if (b == 0) kotlin.math.abs(a) else ucln(b, a % b)
    }
    //Toi gian phan so
    fun toigianphanso(){
        val temp = ucln(tuso,mauso)
        tuso /= temp
        mauso /= temp
        if(mauso<0){ //Chuyen dau am len tren tu neu mau so am
            tuso = -tuso
            mauso = -mauso
        }
    }
    //So sanh phan so: -1 neu nho hon, 0 neu bang, 1 neu lon hon
    fun sosanhphanso(phansokhac:PhanSo):Int{
        val value1 = tuso.toDouble() / mauso
        val value2 = phansokhac.tuso.toDouble() / phansokhac.mauso
        
        return when{
            value1 < value2 -> -1
            value1 == value2 -> 0
            else -> 1
        }
        
    }
    //Tinh tong voi 1 phan so
    fun tong(phanso:PhanSo):PhanSo{
        val tutmp = tuso * phanso.mauso + phanso.tuso * mauso
        val mautmp = mauso * phanso.mauso
        val ans = PhanSo(tutmp, mautmp)
        ans.toigianphanso()
        return ans
    }
    
}

fun main() {
    print("Nhap so luong phan so: ")
    val n = readln().toInt()
    val arr = Array(n) { PhanSo(1, 1) }

    // Nhap mang phan so
    for (i in arr.indices) {
        println("Nhap phan so thu ${i + 1}:")
        arr[i].input()
    }

    println("\nMang phan so vua nhap:")
    arr.forEach { it.output() }

    println("\nMang phan so sau khi toi gian:")
    arr.forEach { it.toigianphanso(); it.output() }

    // Tinh tong cac phan so
    var tong = PhanSo(0, 1)
    for (ps in arr) {
        tong = tong.tong(ps)
    }
    print("\nTong cac phan so = ")
    tong.output()

    // Tim phan so lon nhat
    var max = arr[0]
    for (ps in arr) {
        if (ps.sosanhphanso(max) == 1) max = ps
    }
    print("\nPhan so lon nhat: ")
    max.output()

    // Sap xep giam dan
    arr.sortWith { a, b -> b.sosanhphanso(a) }
    println("\nMang sau khi sap xep giam dan:")
    arr.forEach { it.output() }
}



