import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author : Ahmad Fathanah M.Adil
 * 
 * Admin Access
 * Email    : admin
 * password : admin
 * 
 * Note : Silahkan run Main.java jika program ini ingin dijalankan
 * 
 * */

class jaket {
    private static String jenisBarang = "Jaket";
    private static ArrayList<String> listBarang = new ArrayList<String>();
    private static ArrayList<Integer> listHarga = new ArrayList<Integer>();
    public static int index = 0;
    private int hargaBarang;
    private String namaBarang;

    public jaket(String namaBarang, int hargasatuan) {
        this.hargaBarang = hargasatuan;
        this.namaBarang = namaBarang;
        jaket.listBarang.add(namaBarang);
        jaket.listHarga.add(hargasatuan);
        jaket.index++;
    }

    public static void displayAll() {
        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;
        //memasukan data arraylist_hargasatuan ke dalam variable array_hargabarang
        //agar dapat diakses dengan mudah tiap tiap elementnya
        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        System.out.println("======================================================");
        System.out.println("===================   PT.SHOPAPP   ===================");
        System.out.println("======================================================");
        for (int i = 0; i < jaket.index; i++) {
            System.out.println(""+ (i+1) +". Nama Barang : " + namabarang[i]);
            System.out.printf ("\tHarga  : Rp.%-,1d " , hargabarang[i]);
            if (i == jaket.index-1) {
                System.out.println("\n------------------------------------------------------");
                System.out.println("                                             0. Batal");
                System.out.println("======================================================");
            } else {
                System.out.println("\n");
            }
        }

    }

    public static void tulisDataBarangKefile() throws IOException {
        FileWriter fileoutput = new FileWriter("Daftar_Jaket.txt");
        BufferedWriter writefile = new BufferedWriter(fileoutput);

        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;

        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        for (int i = 0; i < jaket.index; i++) {
            writefile.write(namabarang[i]+","+hargabarang[i]);
            writefile.newLine();
        }

        writefile.flush();
        fileoutput.close();
        writefile.close();
    }

    public static String getJenisBarang() {
        return jaket.jenisBarang;
    }

}

class kemeja {
    private static String jenisBarang = "Kemeja";
    private static ArrayList<String> listBarang = new ArrayList<String>();
    private static ArrayList<Integer> listHarga = new ArrayList<Integer>();
    public static int index = 0;
    private int hargaBarang;
    private String namaBarang;

    public kemeja(String namaBarang, int hargasatuan) {
        this.hargaBarang = hargasatuan;
        this.namaBarang = namaBarang;
        kemeja.listBarang.add(namaBarang);
        kemeja.listHarga.add(hargasatuan);
        kemeja.index++;
    }

    public static void displayAll() {
        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;
        //memasukan data arraylist_hargasatuan ke dalam variable array_hargabarang
        //agar dapat diakses dengan mudah tiap tiap elementnya
        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        System.out.println("======================================================");
        System.out.println("===================   PT.SHOPAPP   ===================");
        System.out.println("======================================================");
        for (int i = 0; i < kemeja.index; i++) {
            System.out.println(""+ (i+1) +". Nama Barang : " + namabarang[i]);
            System.out.printf ("\tHarga  : Rp.%-,1d " , hargabarang[i]);
            if (i == kemeja.index-1) {
                System.out.println("\n------------------------------------------------------");
                System.out.println("                                             0. Batal");
                System.out.println("======================================================");
            } else {
                System.out.println("\n");
            }
        }

    }

    public static void tulisDataBarangKefile() throws IOException {
        FileWriter fileoutput = new FileWriter("Daftar_Kemeja.txt");
        BufferedWriter writefile = new BufferedWriter(fileoutput);

        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;

        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        for (int i = 0; i < kemeja.index; i++) {
            writefile.write(namabarang[i]+","+hargabarang[i]);
            writefile.newLine();
        }

        writefile.flush();
        fileoutput.close();
        writefile.close();
    }

    public static String getJenisBarang() {
        return kemeja.jenisBarang;
    }

}

class jeans {
    private static String jenisBarang = "Jeans";
    private static ArrayList<String> listBarang = new ArrayList<String>();
    private static ArrayList<Integer> listHarga = new ArrayList<Integer>();
    public static int index = 0;
    private int hargaBarang;
    private String namaBarang;

    public jeans(String namaBarang, int hargasatuan) {
        this.hargaBarang = hargasatuan;
        this.namaBarang = namaBarang;
        jeans.listBarang.add(namaBarang);
        jeans.listHarga.add(hargasatuan);
        jeans.index++;
    }

    public static void displayAll() {
        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;
        //memasukan data arraylist_hargasatuan ke dalam variable array_hargabarang
        //agar dapat diakses dengan mudah tiap tiap elementnya
        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        System.out.println("======================================================");
        System.out.println("===================   PT.SHOPAPP   ===================");
        System.out.println("======================================================");
        for (int i = 0; i < jeans.index; i++) {
            System.out.println(""+ (i+1) +". Nama Barang : " + namabarang[i]);
            System.out.printf ("\tHarga  : Rp.%-,1d " , hargabarang[i]);
            if (i == jeans.index-1) {
                System.out.println("\n------------------------------------------------------");
                System.out.println("                                             0. Batal");
                System.out.println("======================================================");
            } else {
                System.out.println("\n");
            }
        }

    }

    public static void tulisDataBarangKefile() throws IOException {
        FileWriter fileoutput = new FileWriter("Daftar_Jeans.txt");
        BufferedWriter writefile = new BufferedWriter(fileoutput);

        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;

        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        for (int i = 0; i < jeans.index; i++) {
            writefile.write(namabarang[i]+","+hargabarang[i]);
            writefile.newLine();
        }

        writefile.flush();
        fileoutput.close();
        writefile.close();
    }

    public static String getJenisBarang() {
        return jeans.jenisBarang;
    }
}

class kaos {
    private static String jenisBarang = "Kaos";
    private static ArrayList<String> listBarang = new ArrayList<String>();
    private static ArrayList<Integer> listHarga = new ArrayList<Integer>();
    public static int index = 0;
    private int hargaBarang;
    private String namaBarang;

    public kaos(String namaBarang, int hargasatuan) {
        this.hargaBarang = hargasatuan;
        this.namaBarang = namaBarang;
        kaos.listBarang.add(namaBarang);
        kaos.listHarga.add(hargasatuan);
        kaos.index++;
    }

    public static void displayAll() {
        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;
        //memasukan data arraylist_hargasatuan ke dalam variable array_hargabarang
        //agar dapat diakses dengan mudah tiap tiap elementnya
        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        System.out.println("======================================================");
        System.out.println("===================   PT.SHOPAPP   ===================");
        System.out.println("======================================================");
        for (int i = 0; i < kaos.index; i++) {
            System.out.println(""+ (i+1) +". Nama Barang : " + namabarang[i]);
            System.out.printf ("\tHarga  : Rp.%-,1d " , hargabarang[i]);
            if (i == kaos.index-1) {
                System.out.println("\n------------------------------------------------------");
                System.out.println("                                             0. Batal");
                System.out.println("======================================================");
            } else {
                System.out.println("\n");
            }
        }

    }

    public static void tulisDataBarangKefile() throws IOException {
        FileWriter fileoutput = new FileWriter("Daftar_Kaos.txt");
        BufferedWriter writefile = new BufferedWriter(fileoutput);

        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;

        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        for (int i = 0; i < kaos.index; i++) {
            writefile.write(namabarang[i]+","+hargabarang[i]);
            writefile.newLine();
        }

        writefile.flush();
        fileoutput.close();
        writefile.close();
    }

    public static String getJenisBarang() {
        return kaos.jenisBarang;
    }
}

class jamtangan {
    private static String jenisBarang = "Jam Tangan";
    private static ArrayList<String> listBarang = new ArrayList<String>();
    private static ArrayList<Integer> listHarga = new ArrayList<Integer>();
    public static int index = 0;
    private int hargaBarang;
    private String namaBarang;

    public jamtangan(String namaBarang, int hargasatuan) {
        this.hargaBarang = hargasatuan;
        this.namaBarang = namaBarang;
        jamtangan.listBarang.add(namaBarang);
        jamtangan.listHarga.add(hargasatuan);
        jamtangan.index++;
    }

    public static void displayAll() {
        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;
        //memasukan data arraylist_hargasatuan ke dalam variable array_hargabarang
        //agar dapat diakses dengan mudah tiap tiap elementnya
        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        System.out.println("======================================================");
        System.out.println("===================   PT.SHOPAPP   ===================");
        System.out.println("======================================================");
        for (int i = 0; i < jamtangan.index; i++) {
            System.out.println(""+ (i+1) +". Nama Barang : " + namabarang[i]);
            System.out.printf ("\tHarga  : Rp.%-,1d " , hargabarang[i]);
            if (i == jamtangan.index-1) {
                System.out.println("\n------------------------------------------------------");
                System.out.println("                                             0. Batal");
                System.out.println("======================================================");
            } else {
                System.out.println("\n");
            }
        }

    }

    public static void tulisDataBarangKefile() throws IOException {
        FileWriter fileoutput = new FileWriter("Daftar_JamTangan.txt");
        BufferedWriter writefile = new BufferedWriter(fileoutput);

        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;

        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        for (int i = 0; i < jamtangan.index; i++) {
            writefile.write(namabarang[i]+","+hargabarang[i]);
            writefile.newLine();
        }

        writefile.flush();
        fileoutput.close();
        writefile.close();
    }

    public static String getJenisBarang() {
        return jamtangan.jenisBarang;
    }
}

class sepatu {
    private static String jenisBarang = "Sepatu";
    private static ArrayList<String> listBarang = new ArrayList<String>();
    private static ArrayList<Integer> listHarga = new ArrayList<Integer>();
    public static int index = 0;
    private int hargaBarang;
    private String namaBarang;

    public sepatu(String namaBarang, int hargasatuan) {
        this.hargaBarang = hargasatuan;
        this.namaBarang = namaBarang;
        sepatu.listBarang.add(namaBarang);
        sepatu.listHarga.add(hargasatuan);
        sepatu.index++;
    }

    public static void displayAll() {
        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;
        //memasukan data arraylist_hargasatuan ke dalam variable array_hargabarang
        //agar dapat diakses dengan mudah tiap tiap elementnya
        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        System.out.println("======================================================");
        System.out.println("===================   PT.SHOPAPP   ===================");
        System.out.println("======================================================");
        for (int i = 0; i < sepatu.index; i++) {
            System.out.println(""+ (i+1) +". Nama Barang : " + namabarang[i]);
            System.out.printf ("\tHarga  : Rp.%-,1d " , hargabarang[i]);
            if (i == sepatu.index-1) {
                System.out.println("\n------------------------------------------------------");
                System.out.println("                                             0. Batal");
                System.out.println("======================================================");
            } else {
                System.out.println("\n");
            }
        }

    }

    public static void tulisDataBarangKefile() throws IOException {
        FileWriter fileoutput = new FileWriter("Daftar_Sepatu.txt");
        BufferedWriter writefile = new BufferedWriter(fileoutput);

        int[] hargabarang = new int[index];
        String[] namabarang = new String[index];
        int indexarray = 0;

        for (int harga:listHarga) {
            hargabarang[indexarray] = harga;
            indexarray++;
        }

        indexarray = 0;
        for (String nama:listBarang) {
            namabarang[indexarray] = nama;
            indexarray++;
        }

        for (int i = 0; i < sepatu.index; i++) {
            writefile.write(namabarang[i]+","+hargabarang[i]);
            writefile.newLine();
        }

        writefile.flush();
        fileoutput.close();
        writefile.close();
    }

    public static String getJenisBarang() {
        return sepatu.jenisBarang;
    }
}

public class barang {

    public static void main() throws IOException {

        /* JIKA INGIN MENAMBAHKAN BARANG BARU, CUKUP MEMBUAT OBJECT BARU  */

        //Jaket
        jaket jaket1 = new jaket("Hoddie", 130000);
        jaket jaket2 = new jaket("Parka", 250000);
        jaket jaket3 = new jaket("Kulit", 300000);
        jaket jaket4 = new jaket("Bomber", 150000);
        jaket jaket5 = new jaket("WindBreker", 280000);
        jaket jaket6 = new jaket("Varsity", 245000);
        jaket jaket7 = new jaket("Pea Coat", 320000);
        jaket jaket8 = new jaket("Over Coat", 399000);

        //kemeja
        kemeja kemeja1 = new kemeja("Flanel", 120000);
        kemeja kemeja2 = new kemeja("Chambray", 90000);
        kemeja kemeja3 = new kemeja("Kantor", 150000);
        kemeja kemeja4 = new kemeja("Batik", 145000);
        kemeja kemeja5 = new kemeja("Denim", 249000);

        //Jeans
        jeans jeans1 = new jeans("Loose Fit Jeans", 120000);
        jeans jeans2 = new jeans("Slim Fit Jeans", 130000);
        jeans jeans3 = new jeans("Tappered Fit Jeans", 150000);
        jeans jeans4 = new jeans("Skinny Jeans", 145000);
        jeans jeans5 = new jeans("Straight Jeans", 135000);
        jeans jeans6 = new jeans("High Waist Jeans", 225000);
        jeans jeans7 = new jeans("Cropped Wide-leg Jeans", 255000);
        jeans jeans8 = new jeans("Wide Leg Jeans", 195000);

        //Kaos
        kaos kaos1 = new kaos("Polo Shirt", 55000);
        kaos kaos2 = new kaos("Kaos Polos Lengan Pendek", 20000);
        kaos kaos3 = new kaos("Kaos Polos Lengan Panjang", 25000);
        kaos kaos4 = new kaos("3SECOND", 70000);

        //Jam Tangan
        jamtangan jamtangan1 = new jamtangan("Casio G-Shock Dw5600Bb", 210000);
        jamtangan jamtangan2 = new jamtangan("Lanccelot Romans King", 710000);
        jamtangan jamtangan3 = new jamtangan("Kinu Classic Sonokeling", 1150000);
        jamtangan jamtangan4 = new jamtangan("Daniel Wellington Classic", 2000000);

        //Sepatu
        sepatu sepatu1 = new sepatu("Nike Air Zoom Pegasus 37", 1799000);
        sepatu sepatu2 = new sepatu("Nike Air Zoom Wildhorse 5", 1649000);
        sepatu sepatu3 = new sepatu("Adidas Aplhabounce", 1699000);
        sepatu sepatu4 = new sepatu("Adidas Duramo 9", 800000);
        sepatu sepatu5 = new sepatu("Converse Chuck 70 Ox", 859000);
        sepatu sepatu6 = new sepatu("Converse Chuck Taylor All Star", 599000);

        jaket.tulisDataBarangKefile();
        kemeja.tulisDataBarangKefile();
        jeans.tulisDataBarangKefile();
        kaos.tulisDataBarangKefile();
        jamtangan.tulisDataBarangKefile();
        sepatu.tulisDataBarangKefile();
    }
}
