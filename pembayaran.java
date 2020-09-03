import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

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

class transfer{
    private long pin;
    private long noRek;
    private long jumlahTransfer;

    transfer(long noRek, long pin, long jumlahTransfer) {
        this.noRek = noRek;
        this.pin = pin;
        this.jumlahTransfer = jumlahTransfer;
    }

    public long getPin() {
        return this.pin;
    }

    public long getNoRek() {
        return this.noRek;
    }

    public  long getJumlahTransfer() {
        return this.jumlahTransfer;
    }
}

public class pembayaran {

    public static void bayar(long bayaran) throws IOException {
        Scanner input = new Scanner(System.in);
        String batal, pilih,pilih2, confirmcod;

        Main.clrscr();

        bayar:
        do {
            System.out.println("======================================================");
            System.out.println("===================   PT.SHOPAPP   ===================");
            System.out.println("======================================================");
            System.out.print  ("Metode Pembayaran : \n" +
                    "\t1. Transfer\n" +
                    "\t2. COD\n" +
                    "======================================================\n" +
                    "Opsi Lain : \n" +
                    "\t9. Back\n" +
                    "\t0. Exit\n");
            System.out.println("======================================================");
            System.out.print  ("Masukkan Pilihan Anda : ");
            pilih = input.next();

            //transfer
            if (pilih.equals("1")) {
                do {
                    System.out.print("\nAnda Memilih Metode Transfer,\nKonfirmasi ? (ya/no) : ");
                    pilih2 = input.next();
                    if (pilih2.equals("ya")) {
                        transfer(bayaran);
                        break bayar;
                    } else if (pilih2.equals("no")) {
                        Main.clrscr();
                        continue bayar;
                    } else {
                        System.err.println("Warning : Masukkan Inputan yang valid ! ");
                    }
                } while ( !pilih2.equals("ya") || !pilih2.equals("no") );

            }

            //cod
            else if (pilih.equals("2")) {
                do {
                    System.out.print("\nAnda Memilih Metode COD,\nKonfirmasi ? (ya/no) : ");
                    pilih2 = input.next();
                    if (pilih2.equals("ya")) {
                        COD(bayaran);
                        do {
                            System.out.print ("Konfirmasi ? (ya/no) : ");
                            confirmcod = input.next();
                            if (confirmcod.equals("ya")) {
                                COD(bayaran);
                                System.out.println("Pesanan Anda Sedang Diproses          ");
                                System.out.println("Terima Kasih Atas Pembeliannya :)     ");
                                System.out.println("======================================================");
                                long biayapajak = get_pajak(bayaran);
                                double biayaongkir = hitungTotalOngkir();
                                long biayaCOD = get_biayaCOD();
                                double subtotal = bayaran + biayaongkir + biayapajak + biayaCOD;
                                writeRiwayat("COD", bayaran, biayaongkir, biayapajak, biayaCOD, subtotal);
                                WriteRiwayatUser("COD",bayaran,biayaongkir,biayapajak,biayaCOD,subtotal);
                                writeAktivitasUser("Telah Melakukan Transaksi COD");
                                break bayar;
                            } else if (confirmcod.equals("no")) {
                                Main.clrscr();
                                continue bayar;
                            } else {
                                System.err.println("Warning : Masukkan Inputan yang valid ! ");
                            }
                        } while ( !confirmcod.equals("ya") || !confirmcod.equals("no") );

                    } else if (pilih2.equals("no")) {
                        Main.clrscr();
                        continue bayar;
                    } else {
                        System.err.println("Warning : Masukkan Inputan yang valid ! ");
                    }
                } while ( !pilih2.equals("ya") || !pilih2.equals("no") );

            }

            //back
            else if (pilih.equals("9")) {
                Main.clrscr();
                shopping.start();
                break;
            }

            //exit
            else if (pilih.equals("0")) {
                do {
                    System.out.print ("Batalkan Pembayaran ? (ya/no) : ");
                    batal = input.next();
                    if (batal.equals("ya")) {
                        Main.canceled();
                        break bayar;
                    } else if (batal.equals("no")) {
                        Main.clrscr();
                        continue bayar;
                    } else {
                        System.err.println("Warning : Masukkan Inputan yang valid ! ");
                    }
                } while ( !batal.equals("ya") || !batal.equals("no") );

            }

            else {
                Main.clrscr();
                System.err.println("Warning : Masukkan Inputan yang valid ! ");
            }

        } while ( !pilih.equals("1") || !pilih.equals("2") );

        File daftarpembelian = new File("Keranjang_User.txt");
        daftarpembelian.delete();

        File datapembeli = new File("datapembeli.txt");
        datapembeli.delete();

        File emailuser = new File("EmailUser.txt");
        emailuser.delete();
    }

    /* TRANSFER */
    private static long get_norek() {
        Scanner input = new Scanner(System.in);
        long norek;

        try {
            System.out.print("Masukkan NO.Rek Anda : ");
            norek = input.nextLong();

            if(norek == 0) {
                System.err.println("Warning : Masukkan NO.Rek yang valid ! ");
                norek = get_norek();
            }
        } catch (InputMismatchException ex) {
            System.err.println("Warning : Masukkan NO.Rek yang valid ! ");
            norek = get_norek();
        }

        return norek;
    }

    private static int get_pin() {
        Scanner input = new Scanner(System.in);
        int pin;

        try {
            System.out.print("Masukkan PIN Anda : ");
            pin = input.nextInt();

            if(pin == 0) {
                System.err.println("Warning : Masukkan PIN yang valid ! ");
                pin = get_pin();
            }
        } catch (InputMismatchException ex) {
            System.err.println("Warning : Masukkan PIN yang valid ! ");
            pin = get_pin();
        }

        return pin;
    }

    private static long get_jumlahtransfer() {
        Scanner input = new Scanner(System.in);
        long jumlahuang;

        try {
            System.out.print("Masukkan Jumlah Transfer : ");
            jumlahuang = input.nextInt();

            if(jumlahuang < 0 || jumlahuang == 0) {
                System.err.println("Warning : Jumlah Yang Anda Masukkan Bernilai 0 ! ");
                jumlahuang = get_jumlahtransfer();
            }
        } catch (InputMismatchException ex) {
            System.err.println("Warning : Masukkan Jumlah Transfer Yang Valid ! ");
            jumlahuang = get_jumlahtransfer();
        }

        return jumlahuang;
    }

    static void transfer(long bayaran) throws IOException {

        FileReader file = new FileReader("EmailUser.txt");
        BufferedReader br = new BufferedReader(file);
        String email = br.readLine();

        Scanner input = new Scanner(System.in);
        long norek, transfer;
        double jumlahbayar;
        int pin;
        String konfirmasi, nokonfirmasi, Lanjut, lanjut_transfer;

        long biayapajak = get_pajak(bayaran);
        double biayaongkir = hitungTotalOngkir();
        double subtotal = bayaran + biayaongkir + biayapajak;
        String addres = getaddres2();
        String addresAdmin = addresAdmin();

        Main.clrscr();

        LOOP:
        do {
            shopping.tampilkanpesanan();
            System.out.println("Jumlah Pesanan     : " + shopping.cekJumlahpesanan());
            System.out.printf ("Total Belanja Anda : Rp.%-,1d \n", bayaran);
            System.out.printf ("Biaya Ongkos Kirim : Rp.%-,1.0f\n", biayaongkir);
            System.out.printf ("Pajak              : Rp.%-,1d\n", biayapajak);
            System.out.println("------------------------------------------------------");
            System.out.printf ("Sub Total : Rp.%-,1.0f\n", subtotal);
            System.out.println("------------------------------------------------------");
            if (email.equalsIgnoreCase("Admin")) {
                System.out.println(addresAdmin);
            } else {
                System.out.println(addres);
            }
            System.out.println("======================================================");
            System.out.print  ("Lanjutkan Transfer ? (ya/no) : ");
            lanjut_transfer = input.next();
            if (lanjut_transfer.equals("ya")) {

                transfer:
                do {
                    Main.clrscr();
                    System.out.println("======================================================");
                    System.out.println("===================   PT.SHOPAPP   ===================");
                    System.out.println("======================================================");
                    System.out.printf ("Sub Total : Rp.%-,1.0f\n", subtotal);
                    System.out.println("------------------------------------------------------");
                    norek = get_norek();
                    pin = get_pin();
                    transfer = get_jumlahtransfer();
                    transfer user = new transfer(norek, pin, transfer);

                    Main.clrscr();
                    do {
                        System.out.println("======================================================");
                        System.out.println("===================   PT.SHOPAPP   ===================");
                        System.out.println("======================================================");
                        System.out.println("No.Rek : " + user.getNoRek());
                        System.out.println("PIN    : " + user.getPin());
                        System.out.printf ("Jumlah : Rp.%-,1d \n", user.getJumlahTransfer());
                        System.out.println("======================================================");

                        System.out.print("Konfirmasi ? (ya/no) : ");
                        konfirmasi = input.next();
                        if (konfirmasi.equals("ya")) {
                            jumlahbayar = transfer - subtotal;
                            if (jumlahbayar == 0) {
                                Main.clrscr();
                                nota_transfer(bayaran);
                                if (email.equalsIgnoreCase("Admin")) {
                                    System.out.println(addresAdmin);
                                } else {
                                    System.out.println(addres);
                                }
                                System.out.println("------------------------------------------------------");
                                System.out.println("Pesanan Anda Sedang Diproses          ");
                                System.out.println("Terima Kasih Atas Pembeliannya :)     ");
                                System.out.println("======================================================");
                                input.close();
                                writeRiwayat("Transfer", bayaran, biayaongkir, biayapajak, 0 ,subtotal);
                                WriteRiwayatUser("Transfer", bayaran, biayaongkir, biayapajak, 0 ,subtotal);
                                writeAktivitasUser("Telah Melakukan Transaksi Transfer");
                                break LOOP;
                            } else if (jumlahbayar > 0) {
                                Main.clrscr();
                                do {
                                    System.out.println("======================================================");
                                    System.out.println("===================   PT.SHOPAPP   ===================");
                                    System.out.println("======================================================");
                                    System.out.println("Jumlah Yang Anda Transfer Melebihi    ");
                                    System.out.println("Sub Total, Silahkan Memasukkan        ");
                                    System.out.println("Jumlah Yang Sesuai !                  ");
                                    System.out.println("======================================================");
                                    System.out.print  ("Transfer Ulang ? (ya/no) : ");

                                    Lanjut = input.next();
                                    if (Lanjut.equals("ya")){
                                        Main.clrscr();
                                        continue transfer;
                                    } else if (Lanjut.equals("no")) {
                                        bayar(bayaran);
                                        break LOOP;
                                    } else {
                                        Main.clrscr();
                                        System.err.println("Warning : Masukkan Inputan yang valid ! ");
                                    }
                                } while ( !(Lanjut.equals("ya") || Lanjut.equals("no")) );
                            } else {
                                Main.clrscr();
                                do {
                                    System.out.println("======================================================");
                                    System.out.println("===================   PT.SHOPAPP   ===================");
                                    System.out.println("======================================================");
                                    System.out.println("Jumlah Yang Anda Transfer Tidak Cukup\n" +
                                            "Silahkan Memasukkan Jumlah Yang Sesuai!");
                                    System.out.println("======================================================");
                                    System.out.print  ("Transfer Ulang ? (ya/no) : ");

                                    Lanjut = input.next();
                                    if (Lanjut.equals("ya")){
                                        Main.clrscr();
                                        continue transfer;
                                    } else if (Lanjut.equals("no")) {
                                        bayar(bayaran);
                                        break LOOP;
                                    } else {
                                        Main.clrscr();
                                        System.err.println("Warning : Masukkan Inputan yang valid ! ");
                                    }
                                } while ( !(Lanjut.equals("ya") || Lanjut.equals("no")) );
                            }

                        }

                        else if (konfirmasi.equals("no")) {
                            do {
                                System.out.print("Tranfer ulang ? (ya/no) : ");
                                nokonfirmasi = input.next();
                                if (nokonfirmasi.equals("ya")) {
                                    Main.clrscr();
                                    continue transfer;
                                } else if (nokonfirmasi.equals("no")) {
                                    bayar(bayaran);
                                    break LOOP;
                                } else {
                                    System.err.println("Warning : Masukkan Inputan yang valid ! ");
                                }
                            } while ( !(nokonfirmasi.equals("ya")) || (nokonfirmasi.equals("no")) );
                        }

                        else {
                            Main.clrscr();
                            System.err.println("Warning : Masukkan Inputan yang valid ! ");
                        }
                    } while ( !(konfirmasi.equals("ya")) || (konfirmasi.equals("no")) );

                } while (true);

            } else if (lanjut_transfer.equals("no")) {
                bayar(bayaran);

            } else {
                Main.clrscr();
                System.err.println("Warning : Masukkan Inputan yang valid ! ");
            }

        } while ( !(lanjut_transfer.equals("ya") || lanjut_transfer.equals("no")) );

        file.close();
        br.close();
    }

    private static void nota_transfer(long totalbayar) throws IOException {

        double subtotal;
        int jumlahpesanan = shopping.cekJumlahpesanan();
        long biayapajak = get_pajak(totalbayar);
        double biayaongkir = hitungTotalOngkir();

        subtotal = totalbayar + biayaongkir + biayapajak;
        shopping.tampilkanpesanan();
        System.out.println("Jumlah Pesanan      : " + jumlahpesanan);
        System.out.printf ("Total harga pesanan : Rp.%-,1d \n", totalbayar);
        System.out.printf ("Biaya Ongkos Kirim  : Rp.%-,1.0f\n", biayaongkir);
        System.out.printf ("Pajak               : Rp.%-,1d\n", biayapajak);
        System.out.println("------------------------------------------------------");
        System.out.printf ("Sub Total           : Rp.%-,1.0f\n", subtotal);
        System.out.println("------------------------------------------------------");
        System.out.println("Status Pembayaran   : Lunas");
        System.out.println("Metode Transaksi    : Transfer");
        System.out.println("======================================================");

    }

    /* COD */
    private static void nota_COD(long totalbayar) throws IOException {

        double subtotal;
        int jumlahpesanan = shopping.cekJumlahpesanan();
        long biayaCOD = get_biayaCOD();
        long biayapajak = get_pajak(totalbayar);
        double biayaongkir = hitungTotalOngkir();

        subtotal = totalbayar + biayaongkir + biayaCOD + biayapajak;
        shopping.tampilkanpesanan();
        System.out.println("Jumlah Pesanan      : " + jumlahpesanan);;
        System.out.printf ("Total harga pesanan : Rp.%-,1d \n", totalbayar);
        System.out.printf ("Biaya Ongkos Kirim  : Rp.%-,1.0f\n", biayaongkir);
        System.out.printf ("Biaya Penanganan    : Rp.%-,1d\n", biayaCOD);
        System.out.printf ("Pajak               : Rp.%-,1d\n", biayapajak);
        System.out.println("------------------------------------------------------");
        System.out.printf ("Sub Total           : Rp.%-,1.0f\n", subtotal);
        System.out.println("------------------------------------------------------");
        System.out.println("Status Pembayaran   : Belum dibayar");
        System.out.println("Metode Transaksi    : COD");
        System.out.println("======================================================");

    }

    private static void COD(long bayaran) throws IOException {
        FileReader file = new FileReader("EmailUser.txt");
        BufferedReader br = new BufferedReader(file);
        String email = br.readLine();
        String addresAdmin = addresAdmin();
        Main.clrscr();

        String addres = getaddres2();

        nota_COD(bayaran);
        if (email.equalsIgnoreCase("Admin")) {
            System.out.println(addresAdmin);
        } else {
            System.out.println(addres);
        }
        System.out.println("------------------------------------------------------");

        file.close();
        br.close();

    }

    private static long get_biayaCOD() throws IOException {
        //biaya penanganan = jumlah pesanan x Rp.3000
        int jumlahpesanan = shopping.cekJumlahpesanan();
        long biaya = jumlahpesanan * 3000;

        return biaya;
    }

    /* PAJAK */
    private static long get_pajak(long totalbayar) {
        //pajak 2% dari total harga
        long biaya = totalbayar * 2 / 100;
        return biaya;
    }

    /* BIAYA ONGKIR */

//    private static double biayaOngkirSepatu() throws IOException {
//         /*  RUMUS BIAYA ONGKIR :
//            ONGKIR = JUMLAH BARANG x BERAT BARANG x  BIAYA ONGKIR PER 1KG + MODAL */
//
//        double ongkir = 0;
//        int berat;
//        int biaya1Kg = 3000;
//        float beratbarang;
//        int modal = 15000;
//
//        File file = new File("Keranjang_User.txt");
//        FileReader fileinput = new FileReader(file);
//        BufferedReader bufferinput = new BufferedReader(fileinput);
//
//        String data = bufferinput.readLine();
//        Scanner datascan;
//
//        while (data != null) {
//            datascan = new Scanner(data);
//            datascan.useDelimiter(",");
//
//            datascan.next();
//            String namabarang = datascan.next();
//            datascan.next();
//            datascan.next();
//            datascan.next();
//            int jumlahbarang = datascan.nextInt();
//
//            if (namabarang.equalsIgnoreCase("Nike Air Zoom Pegasus 37")) {
//                berat = 1000; //beratnya 1000 gram
//                beratbarang = berat / 1000f;
//                ongkir = jumlahbarang * beratbarang * biaya1Kg + modal;
//            } else if (namabarang.equalsIgnoreCase("Nike Air Zoom Wildhorse 5")) {
//                berat = 1000; //beratnya 1000gram
//                beratbarang = berat / 1000f;
//                ongkir = jumlahbarang * beratbarang * biaya1Kg + modal;
//            } else if (namabarang.equalsIgnoreCase("Adidas Aplhabounce")) {
//                berat = 1000; //beratnya 1000gram
//                beratbarang = berat / 1000f;
//                ongkir = jumlahbarang * beratbarang * biaya1Kg + modal;
//            } else if (namabarang.equalsIgnoreCase("Adidas Duramo 9")) {
//                berat = 1000; //beratnya 1000 gram
//                beratbarang = berat / 1000f;
//                ongkir = jumlahbarang * beratbarang * biaya1Kg + modal;
//            } else if (namabarang.equalsIgnoreCase("Converse Chuck 70 Ox")) {
//                berat = 1000; //beratnya 1000 gram
//                beratbarang = berat / 1000f;
//                ongkir = jumlahbarang * beratbarang * biaya1Kg + modal;
//            } else if (namabarang.equalsIgnoreCase("Converse Chuck Taylor All Star")) {
//                berat = 1000; //beratnya 1000 gram
//                beratbarang = berat / 1000f;
//                ongkir = jumlahbarang * beratbarang * biaya1Kg + modal;
//            }
//
//            data = bufferinput.readLine();
//
//        }
//
//        fileinput.close();
//        bufferinput.close();
//
//        return ongkir;
//    }

    private static double hitungTotalOngkir() throws IOException {
        double biayaongkir = 0;
        double[] biaya = new double[50];
        int index = 0;

        int biaya1kg = 3000;
        int modal = 15000;

        int berat_jaket = 600;
        int berat_kemeja = 400;
        int berat_kaos = 300;
        int berat_jeans = 800;
        int berat_jamTangan = 900;
        int berat_sepatu = 1000;

        float beratbarang;

        File file = new File("Keranjang_User.txt");
        FileReader fileinput = new FileReader(file);
        BufferedReader buffferinput = new BufferedReader(fileinput);

        String data = buffferinput.readLine();
        Scanner datascan;

        while ( data != null) {
            datascan = new Scanner(data);
            datascan.useDelimiter(",");
            String kategori = datascan.next();
            String namabarang = datascan.next();
            datascan.next();
            datascan.next();
            datascan.next();
            int jumlahbarang = datascan.nextInt();

            if (kategori.equalsIgnoreCase("Jaket")) {
                beratbarang = berat_jaket / 1000f;
                biaya[index] = jumlahbarang * beratbarang * biaya1kg + modal;
            } else if (kategori.equalsIgnoreCase("Kemeja")) {
                beratbarang = berat_kemeja / 1000f;
                biaya[index] = jumlahbarang * beratbarang * biaya1kg + modal;
            } else if (kategori.equalsIgnoreCase("Jeans")) {
                beratbarang = berat_jeans / 1000f;
                biaya[index] = jumlahbarang * beratbarang * biaya1kg + modal;
            } else if (kategori.equalsIgnoreCase("Kaos")) {
                beratbarang = berat_kaos / 1000f;
                biaya[index] = jumlahbarang * beratbarang * biaya1kg + modal;
            } else if (kategori.equalsIgnoreCase("Jam Tangan")) {
                beratbarang = berat_jamTangan / 1000f;
                biaya[index] = jumlahbarang * beratbarang * biaya1kg + modal;
            } else if (kategori.equalsIgnoreCase("Sepatu")) {
                beratbarang = berat_sepatu / 1000f;
                biaya[index] = jumlahbarang * beratbarang * biaya1kg + modal;
            }

            biayaongkir += biaya[index];
            index++;
            data = buffferinput.readLine();

        }

        fileinput.close();
        buffferinput.close();

        return biayaongkir;
    }

    /* SIMPAN RIWAYAT TRANSAKSI KE FILE */
    public static void writeRiwayat(String metodepembayaran, long totalharga, double ongkir, long pajak, long biayaCOD, double subtotal) throws IOException {

        String harga = String.format("%-,1d", totalharga);
        String biayaongkir = String.format("%-,1.0f" ,ongkir );
        String biayapajak = String.format("%-,1d", pajak);
        String biayacod = String.format("%-,1d", biayaCOD);
        String subTotal = String.format("%-,1.0f",subtotal);

        //file daftar pembelian
        FileReader filepembelian = new FileReader("Keranjang_User.txt");
        BufferedReader BufferFilepembelian = new BufferedReader(filepembelian);
        String dataFilePembelian = BufferFilepembelian.readLine();

        //file data user
        String datauser = addresUntukriwayat();
        String addresAdmin = addresAdminRiwayat();

        //file email user yang digunakan
        FileReader emailuser = new FileReader("EmailUser.txt");
        BufferedReader getEmailUserYangDigunakan  = new BufferedReader(emailuser);
        String EmailyangDigunakan = getEmailUserYangDigunakan.readLine();

        //file riwayat
        FileWriter fileDatabase = new FileWriter("RiwayatTransaksi.txt", true);
        BufferedWriter writeRiwayat = new BufferedWriter(fileDatabase);

        FileReader filedatabase = new FileReader("RiwayatTransaksi.txt");
        BufferedReader ReadDatariwayat = new BufferedReader(filedatabase);
        String dataRiwayat = ReadDatariwayat.readLine();

        //Ambil Nomor Riwayat
        int jumlahriwayat = 0;
        while (dataRiwayat != null) {
            if (dataRiwayat.contains("*****")) {
                jumlahriwayat++;
            }
            dataRiwayat = ReadDatariwayat.readLine();
        }

        //Ambil Data Pesanan User
        writeRiwayat.write("No."+ (jumlahriwayat+1) );
        writeRiwayat.newLine();
        writeRiwayat.write("\tPesanan : ");
        writeRiwayat.newLine();
        writeRiwayat.write("\t---------------------------------------------------------------");
        writeRiwayat.newLine();
        writeRiwayat.write("\tKategori,NamaBarang,HargaSatuan,Warna,Ukuran,Jumlah,TotalHarga");
        writeRiwayat.newLine();

        int jumlahpesanan = 0;
        while (dataFilePembelian != null) {
            jumlahpesanan++;
            writeRiwayat.write("\t"+jumlahpesanan+". "+dataFilePembelian);
            writeRiwayat.newLine();

            dataFilePembelian = BufferFilepembelian.readLine();
        }
        writeRiwayat.write("\tJumlah Pesanan : "  + jumlahpesanan);
        writeRiwayat.newLine();
        writeRiwayat.write("\t---------------------------------------------------------------");
        writeRiwayat.newLine();

        //ambil data user
        writeRiwayat.write("\tEmail           : " + EmailyangDigunakan);
        writeRiwayat.newLine();
        if (EmailyangDigunakan.equalsIgnoreCase("Admin")) {
            writeRiwayat.write(addresAdmin);
            writeRiwayat.newLine();
        } else {
            writeRiwayat.write(datauser);
            writeRiwayat.newLine();
        }
        writeRiwayat.write("\t------------------------------------          *****");
        writeRiwayat.newLine();
        writeRiwayat.write("\tMetode Pembayaran   : "  + metodepembayaran);
        writeRiwayat.newLine();
        writeRiwayat.write("\tTotal Harga Pesanan : Rp." + harga);
        writeRiwayat.newLine();
        writeRiwayat.write("\tBiaya Ongkir        : Rp." + biayaongkir);
        writeRiwayat.newLine();
        writeRiwayat.write("\tBiaya Pajak         : Rp." + biayapajak);
        writeRiwayat.newLine();
        if (metodepembayaran.equalsIgnoreCase("COD")) {
            writeRiwayat.write("\tBiaya Penanganan    : Rp." + biayacod);
            writeRiwayat.newLine();
        }
        writeRiwayat.write("\t------------------------------------");
        writeRiwayat.newLine();
        writeRiwayat.write("\tSub Total           : Rp." + subTotal);
        writeRiwayat.newLine();
        writeRiwayat.write("=====================================================================");
        writeRiwayat.newLine();

        writeRiwayat.flush();

        filepembelian.close();
        BufferFilepembelian.close();
        emailuser.close();
        getEmailUserYangDigunakan.close();

        fileDatabase.close();
        writeRiwayat.close();
        fileDatabase.close();
        ReadDatariwayat.close();

    }

    public static void writeAktivitasUser(String aktivitas) throws IOException {
        FileReader file = new FileReader("EmailUser.txt");
        BufferedReader bufferemail = new BufferedReader(file);
        String email = bufferemail.readLine();

        FileWriter filewrite = new FileWriter("AktivitasUser.txt", true);
        BufferedWriter writeAktivitas = new BufferedWriter(filewrite);

        writeAktivitas.write(email+ " ---> " + aktivitas);
        writeAktivitas.newLine();
        writeAktivitas.write("-----------------------------------------------------------");
        writeAktivitas.newLine();

        writeAktivitas.flush();

        file.close();
        bufferemail.close();
        filewrite.close();
        writeAktivitas.close();
    }

    public static void WriteRiwayatUser(String metodepembayaran, long totalharga, double ongkir, long pajak, long biayaCOD, double subtotal) throws IOException {

        //file email yang login
        FileReader file = new FileReader("EmailUser.txt");
        BufferedReader fileemail = new BufferedReader(file);
        String emailyangLogin = fileemail.readLine();

        //file data pembelian user
        FileReader filebeli = new FileReader("Keranjang_User.txt");
        BufferedReader bufferpembelian = new BufferedReader(filebeli);
        String datapembelian = bufferpembelian.readLine();

        //file data riwayat user
        String filedata = "dataRiwayat_" + emailyangLogin + ".txt";
        FileWriter filewrite = new FileWriter(filedata, true);
        BufferedWriter writeRiwayatuser = new BufferedWriter(filewrite);

        FileReader riwayat = new FileReader(filedata);
        BufferedReader bufferread = new BufferedReader(riwayat);
        String dataRiwayat = bufferread.readLine();

        //Ambil Nomor Riwayat
        int jumlahriwayat = 0;
        while (dataRiwayat != null) {
            if (dataRiwayat.contains("*****")) {
                jumlahriwayat++;
            }
            dataRiwayat = bufferread.readLine();
        }

        writeRiwayatuser.write("No." + (jumlahriwayat+1));
        writeRiwayatuser.newLine();

        //ambil data pembelian user
        writeRiwayatuser.write("\tPesanan : ");
        writeRiwayatuser.newLine();
        writeRiwayatuser.write("\t--------------------------------------------------------------");
        writeRiwayatuser.newLine();
        writeRiwayatuser.write("\tKategori,NamaBarang,HargaSatuan,Warna,Ukuran,Jumlah,TotalHarga");
        writeRiwayatuser.newLine();

        int jumlahpesanan = 0;
        while (datapembelian != null) {
            jumlahpesanan++;
            writeRiwayatuser.write("\t"+jumlahpesanan+ ". " + datapembelian);
            writeRiwayatuser.newLine();
            datapembelian = bufferpembelian.readLine();
        }
        writeRiwayatuser.write("\tJumlah Pesanan : "  + jumlahpesanan);
        writeRiwayatuser.newLine();
        writeRiwayatuser.write("\t---------------------------------         *****");
        writeRiwayatuser.newLine();

        //ambil total harga
        String harga = String.format("%-,1d", totalharga);
        String biayaongkir = String.format("%-,1.0f" ,ongkir );
        String biayapajak = String.format("%-,1d", pajak);
        String biayacod = String.format("%-,1d", biayaCOD);
        String subTotal = String.format("%-,1.0f",subtotal);

        writeRiwayatuser.write("\tMetode Pembayaran   : " + metodepembayaran);
        writeRiwayatuser.newLine();
        writeRiwayatuser.write("\tTotal Harga Pesanan : Rp." + harga);
        writeRiwayatuser.newLine();
        writeRiwayatuser.write("\tBiaya Ongkir        : Rp." + biayaongkir);
        writeRiwayatuser.newLine();
        writeRiwayatuser.write("\tBiaya Pajak         : Rp." + biayapajak);
        writeRiwayatuser.newLine();

        if (metodepembayaran.equals("COD")) {
            writeRiwayatuser.write("\tBiaya Penanganan    : Rp." + biayacod);
            writeRiwayatuser.newLine();
        }

        writeRiwayatuser.write("\t---------------------------------");
        writeRiwayatuser.newLine();
        writeRiwayatuser.write("\tSub Total           : Rp." + subTotal);
        writeRiwayatuser.newLine();
        writeRiwayatuser.write("=====================================================================");
        writeRiwayatuser.newLine();

        writeRiwayatuser.flush();

        file.close();
        fileemail.close();
        filebeli.close();
        bufferpembelian.close();
        filewrite.close();
        writeRiwayatuser.close();
        riwayat.close();
        bufferread.close();

    }

    private static String addresUntukriwayat() throws IOException {
        FileReader file = new FileReader("Email&Password_User.txt");
        BufferedReader ReadFile = new BufferedReader(file);

        FileReader fileemail = new FileReader("EmailUser.txt");
        BufferedReader bufferinput = new BufferedReader(fileemail);

        String alamatuser = "";
        String email = bufferinput.readLine();
        String data = ReadFile.readLine();
        while (data != null) {
            Scanner datascan = new Scanner(data);
            datascan.useDelimiter("/");
            String emailuser = datascan.next();

            if (emailuser.equals(email)) {
                Scanner datascan2 = new Scanner(data);
                datascan2.useDelimiter("_");
                datascan2.next();
                String user = datascan2.next();
                datascan2 = new Scanner(user);
                datascan2.useDelimiter(",");
                String nama = datascan2.next();
                String nomorhp = datascan2.next();
                String kota = datascan2.next();
                String prov = datascan2.next();
                alamatuser = String.format("\t%s, %s\n\tAkan Dikirim Ke :\n\t%s, %s",nama,nomorhp,kota,prov);
                break;

            }

            data = ReadFile.readLine();
        }

        file.close();
        ReadFile.close();
        fileemail.close();
        bufferinput.close();

        return alamatuser;

    }

    private static String addresAdmin()  {

        String alamatAdmin = "ADMIN ACCESS\n" +
                "Nama Admin : Ahmad Fathanah M.Adil\n" +
                "Nomor Hp   : 082291453878\n" +
                "Alamat     : Kendari, Sulawesi Tenggara";

        return alamatAdmin;
    }

    private static String addresAdminRiwayat()  {

        String alamatAdmin = "\tADMIN ACCESS\n" +
                "\tNama Admin : Ahmad Fathanah M.Adil\n" +
                "\tNomor Hp   : 082291453878\n" +
                "\tAlamat     : Kendari, Sulawesi Tenggara";

        return alamatAdmin;
    }

    private static String getaddres2() throws IOException {

        FileReader file = new FileReader("Email&Password_User.txt");
        BufferedReader ReadFile = new BufferedReader(file);

        FileReader fileemail = new FileReader("EmailUser.txt");
        BufferedReader bufferinput = new BufferedReader(fileemail);

        String alamatuser = "";
        String email = bufferinput.readLine();
        String data = ReadFile.readLine();
        while (data != null) {
            Scanner datascan = new Scanner(data);
            datascan.useDelimiter("/");
            String emailuser = datascan.next();

            if (emailuser.equals(email)) {
                Scanner datascan2 = new Scanner(data);
                datascan2.useDelimiter("_");
                datascan2.next();
                String user = datascan2.next();
                datascan2 = new Scanner(user);
                datascan2.useDelimiter(",");
                String nama = datascan2.next();
                String nomorhp = datascan2.next();
                String kota = datascan2.next();
                String prov = datascan2.next();
                alamatuser = String.format("%s, %s\nAkan Dikirim Ke :\n%s, %s",nama,nomorhp,kota,prov);
                break;
            }



            data = ReadFile.readLine();
        }

        file.close();
        ReadFile.close();
        fileemail.close();
        bufferinput.close();

        return alamatuser;
    }

}
