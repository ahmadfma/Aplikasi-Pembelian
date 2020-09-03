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

public class shopping {

    public static void main() throws IOException {
        FileWriter file = new FileWriter("Keranjang_User.txt");
        file.close();
        start();
    }

    private static void Menu_utama() throws IOException {
        System.out.println("======================================================");
        System.out.println("===================   PT.SHOPAPP   ===================");
        System.out.println("======================================================");
        System.out.println("Kategori Yang Tersedia :\n" +
                "\t1. Jaket\t\t4. Kaos\n" +
                "\t2. Kemeja\t\t5. Jam Tangan\n" +
                "\t3. Jeans\t\t6. Sepatu");
        System.out.println("                            ==========================");
        System.out.println("                            | Jumlah Pesanan : " + cekJumlahpesanan());
        System.out.println("======================================================");
        System.out.println("Opsi Lain : ");
        System.out.println("\t7. Lihat Pesanan\n" +
                "\t8. Hapus Pesanan\n" +
                "\t9. Edit Pesanan\n" +
                "\t0. Edit Profil\n" +
                "\tq. Riwayat Transaksi saya\n" +
                "\tw. Log out\n" +
                "\te. Exit");
        System.out.println("======================================================");

    }

    public static void start() throws IOException {
        Scanner input = new Scanner(System.in);
        boolean loop = true;

        LOOP:
        do {
            Menu_utama();
            System.out.print("Masukkan Pilihan Anda : ");
            String pilihan = input.next();

            //JAKET
            if (pilihan.equals("1")) {
                Main.clrscr();
                jaket.displayAll();
                int numPilihan = getNumPilihan(jaket.index);
                if (numPilihan == 0) {
                    Main.clrscr();
                    continue LOOP;
                }
                int jumlah = getAmount();
                String warna = getWarna();
                String ukuran = getUkuran();
                pemesanan_jaket(numPilihan, jumlah, warna, ukuran);
            }

            //KEMEJA
            else if (pilihan.equals("2")) {
                Main.clrscr();
                kemeja.displayAll();
                int numPilihan = getNumPilihan(kemeja.index);
                if (numPilihan == 0) {
                    Main.clrscr();
                    continue LOOP;
                }
                int jumlah = getAmount();
                String warna = getWarna();
                String ukuran = getUkuran();
                pemesanan_kemeja(numPilihan, jumlah, warna, ukuran);
            }

            //JEANS
            else if (pilihan.equals("3")) {
                Main.clrscr();
                jeans.displayAll();
                int numPilihan = getNumPilihan(jeans.index);
                if (numPilihan == 0) {
                    Main.clrscr();
                    continue LOOP;
                }
                int jumlah = getAmount();
                String warna = getWarna();
                String ukuran = getUkuran();
                pemesanan_jeans(numPilihan, jumlah, warna, ukuran);
            }

            //KAOS
            else if (pilihan.equals("4")) {
                Main.clrscr();
                kaos.displayAll();
                int numPilihan = getNumPilihan(kaos.index);
                if (numPilihan == 0) {
                    Main.clrscr();
                    continue LOOP;
                }
                int jumlah = getAmount();
                String warna = getWarna();
                String ukuran = getUkuran();
                pemesanan_kaos(numPilihan, jumlah, warna, ukuran);
            }

            //JAM TANGAN
            else if (pilihan.equals("5")) {
                Main.clrscr();
                jamtangan.displayAll();
                int numPilihan = getNumPilihan(jamtangan.index);
                if (numPilihan == 0) {
                    Main.clrscr();
                    continue LOOP;
                }
                int jumlah = getAmount();
                String warna = getWarna();
                pemesanan_jamtangan(numPilihan, jumlah, warna, "All Size");
            }

            //SEPATU
            else if (pilihan.equals("6")) {
                Main.clrscr();
                sepatu.displayAll();
                int numPilihan = getNumPilihan(sepatu.index);
                if (numPilihan == 0) {
                    Main.clrscr();
                    continue LOOP;
                }
                int jumlah = getAmount();
                String warna = getWarna();
                String ukuran = getUkuranSepatu();
                pemesanan_sepatu(numPilihan, jumlah, warna, ukuran);
            }

            //LIHAT PESANAN
            else if (pilihan.equals("7")) {
                int jumlahpesanan = cekJumlahpesanan();
                String pesanlagi, lanjut_bayar;
                long totalbayar;
                if (jumlahpesanan > 0) {
                    Main.clrscr();
                    do {
                        tampilkanpesanan();
                        get_totalbayar();

                        /* PESAN LAGI ?*/
                        System.out.println("             1. Back      |     2. Bayar");
                        System.out.println("------------------------------------------------------");
                        System.out.print  ("Masukkan Pilihan Anda : ");
                        pesanlagi = input.next();

                        if (pesanlagi.equals("1")) {
                            Main.clrscr();
                            continue LOOP;
                        } else if (pesanlagi.equals("2")) {
                            Main.clrscr();
                            if (jumlahpesanan > 0) {
                                tampilkanpesanan();
                                totalbayar = get_totalbayar();
                                do {
                                    System.out.print("Lanjutkan Pembayaran ? (ya/no) : ");
                                    lanjut_bayar = input.next();
                                    if (lanjut_bayar.equals("ya")) {
                                        pembayaran.bayar(totalbayar);
                                        break LOOP;
                                    } else if (lanjut_bayar.equals("no")) {
                                        Main.clrscr();
                                        continue LOOP;
                                    } else {
                                        System.err.println("Warning : Masukkan Inputan yang valid ! ");
                                    }

                                } while ( !lanjut_bayar.equals("ya") || !lanjut_bayar.equals("no") );

                            } else {
                                Main.canceled();
                                break LOOP;
                            }
                        } else {
                            Main.clrscr();
                            System.err.println("Warning : Masukkan Inputan yang valid ! ");
                        }

                    } while ( !(pesanlagi.equals("ya") || pesanlagi.equals("no")) );

                } else {
                    Main.clrscr();
                    System.out.println("Warning : Keranjang Anda Kosong, Silahkan Memesan");
                }
            }

            //HAPUS PESANAN
            else if (pilihan.equals("8")) {
                int jumlahpesanan = cekJumlahpesanan();
                if (jumlahpesanan > 0) {
                    hapuspesanan();
                } else {
                    Main.clrscr();
                    System.out.println("Warning : Keranjang Anda Sudah Kosong !");
                }
            }

            //EDIT PESANAN
            else if (pilihan.equals("9")) {
                int jumlahpesanan = cekJumlahpesanan();
                if (jumlahpesanan > 0) {
                    editpesanan();
                } else {
                    Main.clrscr();
                    System.out.println("Warning : Keranjang Anda Kosong !");
                }
            }

            //EDIT DATA
            else if (pilihan.equals("0"))  {
                Main.clrscr();
                editData();
                loop = true;
            }

            //RIWAYAT USER
            else if (pilihan.equals("q")) {
                boolean isLanjut = readriwayatuser();
                if (isLanjut) {
                    continue LOOP;
                } else {
                    int jumlahpesanan = cekJumlahpesanan();
                    if (jumlahpesanan > 0) {
                        boolean isExit = getYesorNo("Anda Telah Memesan Barang,\nTetap Ingin Keluar ? (ya/no) : ");
                        if (isExit) {
                            Main.canceled();
                            break LOOP;
                        } else {
                            Main.clrscr();
                            continue LOOP;
                        }
                    } else {
                        Main.canceled();
                        break LOOP;
                    }
                }
            }

            //LOGOUT
            else if (pilihan.equals("w")) {
                int jumlahpesanan = cekJumlahpesanan();
                if (jumlahpesanan > 0) {
                    boolean isLogout = getYesorNo("Anda Telah Memesan Barang,\nTetap Ingin Log out ? (ya/no) : ");
                    if (isLogout) {
                        File file = new File("EmailUser.txt");
                        file.delete();
                        Main.Start();
                        break LOOP;
                    }
                    Main.clrscr();
                }
                else {
                    boolean isLogout = getYesorNo("Ingin Log out ? (ya/no) : ");
                    if (isLogout) {
                        File file = new File("EmailUser.txt");
                        file.delete();
                        Main.Start();
                        break LOOP;
                    }
                    Main.clrscr();
                }
            }

            //EXIT
            else if (pilihan.equals("e")) {
                int jumlahpesanan = cekJumlahpesanan();
                if (jumlahpesanan > 0) {
                    boolean isExit = getYesorNo("Anda Telah Memesan Barang,\nTetap Ingin Keluar ? (ya/no) : ");
                    if (isExit) {
                        File file = new File("EmailUser.txt");
                        file.delete();
                        Main.canceled();
                        break LOOP;
                    } else {
                        Main.clrscr();
                        continue LOOP;
                    }
                }
                else {
                    boolean isExit = getYesorNo("Ingin Keluar dari Program ? (ya/no) : ");
                    if (isExit) {
                        File file = new File("EmailUser.txt");
                        file.delete();
                        Main.canceled();
                        break LOOP;
                    } else {
                        Main.clrscr();
                        continue LOOP;
                    }
                }
            }

            else {
                Main.clrscr();
                System.out.println("Pilihan Tidak Tersedia !");
            }
        } while (loop);

    }

    /* PEMESANAN */
    private static void pemesanan_jaket(int buyNum, int jumlah, String warna, String ukuran) throws IOException {
        FileReader file = new FileReader("Daftar_Jaket.txt");
        BufferedReader fileinput = new BufferedReader(file);
        String data = fileinput.readLine();
        int entryNum = 0;

        while (data != null) {
            entryNum++;

            if (entryNum == buyNum) {
                Scanner datascan = new Scanner(data);
                datascan.useDelimiter(",");
                String namabarang = datascan.next();
                long hargabarang = datascan.nextLong();
                Main.clrscr();
                System.out.println("======================================================");
                System.out.println("===================   PT.SHOPAPP   ===================");
                System.out.println("======================================================");
                System.out.println("Anda Ingin Memesan :");
                System.out.println("\tKategori       : " + jaket.getJenisBarang());
                System.out.println("\tNama Barang    : " + namabarang);
                System.out.printf ("\tHarga Satuan   : Rp.%-,1d \n", hargabarang);
                System.out.println("\tWarna Pilihan  : " + warna);
                System.out.println("\tUkuran Pilihan : " + ukuran);
                System.out.println("\tJumlah         : " + jumlah);
                long totalharga = jumlah * hargabarang;
                System.out.printf ("\tTotal Harga    : Rp.%-,1d \n", totalharga);
                System.out.println("======================================================");
                boolean isPesan = getYesorNo("Masukkan Ke Keranjang ? (ya/no) : ");
                String dataPemesanan = jaket.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran+","+jumlah+","+totalharga;
                String cekPesanan = jaket.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran;
                if (isPesan) {
                    file.close();
                    fileinput.close();
                    boolean isExist = cekIsiKeranjang(cekPesanan);
                    if (isExist) {
                        JumlahkanJumlahdanHarganya(cekPesanan,jumlah,totalharga);
                    } else {
                        masukkanKeKeranjang(dataPemesanan);
                        Main.clrscr();
                        System.out.println("Pesanan Dimasukkan Ke Keranjang Anda");
                    }
                } else {
                    Main.clrscr();
                    System.out.println("Pemesanan Dibatalkan");
                }
                break;
            }


            data = fileinput.readLine();
        }

        file.close();
        fileinput.close();

        file.close();
        fileinput.close();
    }

    private static void pemesanan_kemeja(int buyNum, int jumlah, String warna, String ukuran) throws IOException {
        FileReader file = new FileReader("Daftar_Kemeja.txt");
        BufferedReader fileinput = new BufferedReader(file);
        String data = fileinput.readLine();
        int entryNum = 0;

        while (data != null) {
            entryNum++;

            if (entryNum == buyNum) {
                Scanner datascan = new Scanner(data);
                datascan.useDelimiter(",");
                String namabarang = datascan.next();
                long hargabarang = datascan.nextLong();
                Main.clrscr();
                System.out.println("======================================================");
                System.out.println("===================   PT.SHOPAPP   ===================");
                System.out.println("======================================================");
                System.out.println("Anda Ingin Memesan :");
                System.out.println("\tKategori       : " + kemeja.getJenisBarang());
                System.out.println("\tNama Barang    : " + namabarang);
                System.out.printf ("\tHarga Satuan   : Rp.%-,1d \n", hargabarang);
                System.out.println("\tWarna Pilihan  : " + warna);
                System.out.println("\tUkuran Pilihan : " + ukuran);
                System.out.println("\tJumlah         : " + jumlah);
                long totalharga = jumlah * hargabarang;
                System.out.printf ("\tTotal Harga    : Rp.%-,1d \n", totalharga);
                System.out.println("======================================================");
                boolean isPesan = getYesorNo("Masukkan Ke Keranjang ? (ya/no) : ");
                String dataPemesanan = kemeja.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran+","+jumlah+","+totalharga;
                String cekPesanan = kemeja.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran;
                if (isPesan) {
                    file.close();
                    fileinput.close();
                    boolean isExist = cekIsiKeranjang(cekPesanan);
                    if (isExist) {
                        JumlahkanJumlahdanHarganya(cekPesanan,jumlah,totalharga);
                    } else {
                        masukkanKeKeranjang(dataPemesanan);
                        Main.clrscr();
                        System.out.println("Pesanan Dimasukkan Ke Keranjang Anda");
                    }
                } else {
                    Main.clrscr();
                    System.out.println("Pemesanan Dibatalkan");
                }
                break;
            }


            data = fileinput.readLine();
        }

        file.close();
        fileinput.close();
    }

    private static void pemesanan_jeans(int buyNum, int jumlah, String warna, String ukuran) throws IOException {
        FileReader file = new FileReader("Daftar_Jeans.txt");
        BufferedReader fileinput = new BufferedReader(file);
        String data = fileinput.readLine();
        int entryNum = 0;

        while (data != null) {
            entryNum++;

            if (entryNum == buyNum) {
                Scanner datascan = new Scanner(data);
                datascan.useDelimiter(",");
                String namabarang = datascan.next();
                long hargabarang = datascan.nextLong();
                Main.clrscr();
                System.out.println("======================================================");
                System.out.println("===================   PT.SHOPAPP   ===================");
                System.out.println("======================================================");
                System.out.println("Anda Ingin Memesan :");
                System.out.println("\tKategori       : " + jeans.getJenisBarang());
                System.out.println("\tNama Barang    : " + namabarang);
                System.out.printf ("\tHarga Satuan   : Rp.%-,1d \n", hargabarang);
                System.out.println("\tWarna Pilihan  : " + warna);
                System.out.println("\tUkuran Pilihan : " + ukuran);
                System.out.println("\tJumlah         : " + jumlah);
                long totalharga = jumlah * hargabarang;
                System.out.printf ("\tTotal Harga    : Rp.%-,1d \n", totalharga);
                System.out.println("======================================================");
                boolean isPesan = getYesorNo("Masukkan Ke Keranjang ? (ya/no) : ");
                String dataPemesanan = jeans.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran+","+jumlah+","+totalharga;
                String cekPesanan = jeans.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran;
                if (isPesan) {
                    file.close();
                    fileinput.close();
                    boolean isExist = cekIsiKeranjang(cekPesanan);
                    if (isExist) {
                        JumlahkanJumlahdanHarganya(cekPesanan,jumlah,totalharga);
                    } else {
                        masukkanKeKeranjang(dataPemesanan);
                        Main.clrscr();
                        System.out.println("Pesanan Dimasukkan Ke Keranjang Anda");
                    }
                } else {
                    Main.clrscr();
                    System.out.println("Pemesanan Dibatalkan");
                }
                break;
            }


            data = fileinput.readLine();
        }

        file.close();
        fileinput.close();
    }

    private static void pemesanan_kaos(int buyNum, int jumlah, String warna, String ukuran) throws IOException {
        FileReader file = new FileReader("Daftar_Kaos.txt");
        BufferedReader fileinput = new BufferedReader(file);
        String data = fileinput.readLine();
        int entryNum = 0;

        while (data != null) {
            entryNum++;

            if (entryNum == buyNum) {
                Scanner datascan = new Scanner(data);
                datascan.useDelimiter(",");
                String namabarang = datascan.next();
                long hargabarang = datascan.nextLong();
                Main.clrscr();
                System.out.println("======================================================");
                System.out.println("===================   PT.SHOPAPP   ===================");
                System.out.println("======================================================");
                System.out.println("Anda Ingin Memesan :");
                System.out.println("\tKategori       : " + kaos.getJenisBarang());
                System.out.println("\tNama Barang    : " + namabarang);
                System.out.printf ("\tHarga Satuan   : Rp.%-,1d \n", hargabarang);
                System.out.println("\tWarna Pilihan  : " + warna);
                System.out.println("\tUkuran Pilihan : " + ukuran);
                System.out.println("\tJumlah         : " + jumlah);
                long totalharga = jumlah * hargabarang;
                System.out.printf ("\tTotal Harga    : Rp.%-,1d \n", totalharga);
                System.out.println("======================================================");
                boolean isPesan = getYesorNo("Masukkan Ke Keranjang ? (ya/no) : ");
                String dataPemesanan = kaos.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran+","+jumlah+","+totalharga;
                String cekPesanan = kaos.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran;
                if (isPesan) {
                    file.close();
                    fileinput.close();
                    boolean isExist = cekIsiKeranjang(cekPesanan);
                    if (isExist) {
                        JumlahkanJumlahdanHarganya(cekPesanan,jumlah,totalharga);
                    } else {
                        masukkanKeKeranjang(dataPemesanan);
                        Main.clrscr();
                        System.out.println("Pesanan Dimasukkan Ke Keranjang Anda");
                    }
                } else {
                    Main.clrscr();
                    System.out.println("Pemesanan Dibatalkan");
                }
                break;
            }


            data = fileinput.readLine();
        }

        file.close();
        fileinput.close();
    }

    private static void pemesanan_jamtangan(int buyNum, int jumlah, String warna, String ukuran) throws IOException {
        FileReader file = new FileReader("Daftar_JamTangan.txt");
        BufferedReader fileinput = new BufferedReader(file);
        String data = fileinput.readLine();
        int entryNum = 0;

        while (data != null) {
            entryNum++;

            if (entryNum == buyNum) {
                Scanner datascan = new Scanner(data);
                datascan.useDelimiter(",");
                String namabarang = datascan.next();
                long hargabarang = datascan.nextLong();
                Main.clrscr();
                System.out.println("======================================================");
                System.out.println("===================   PT.SHOPAPP   ===================");
                System.out.println("======================================================");
                System.out.println("Anda Ingin Memesan :");
                System.out.println("\tKategori       : " + jamtangan.getJenisBarang());
                System.out.println("\tNama Barang    : " + namabarang);
                System.out.printf ("\tHarga Satuan   : Rp.%-,1d \n", hargabarang);
                System.out.println("\tWarna Pilihan  : " + warna);
                System.out.println("\tUkuran Pilihan : " + ukuran);
                System.out.println("\tJumlah         : " + jumlah);
                long totalharga = jumlah * hargabarang;
                System.out.printf ("\tTotal Harga    : Rp.%-,1d \n", totalharga);
                System.out.println("======================================================");
                boolean isPesan = getYesorNo("Masukkan Ke Keranjang ? (ya/no) : ");
                String dataPemesanan = jamtangan.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran+","+jumlah+","+totalharga;
                String cekPesanan = jamtangan.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran;
                if (isPesan) {
                    file.close();
                    fileinput.close();
                    boolean isExist = cekIsiKeranjang(cekPesanan);
                    if (isExist) {
                        JumlahkanJumlahdanHarganya(cekPesanan,jumlah,totalharga);
                    } else {
                        masukkanKeKeranjang(dataPemesanan);
                        Main.clrscr();
                        System.out.println("Pesanan Dimasukkan Ke Keranjang Anda");
                    }
                } else {
                    Main.clrscr();
                    System.out.println("Pemesanan Dibatalkan");
                }
                break;
            }


            data = fileinput.readLine();
        }

        file.close();
        fileinput.close();
    }

    private static void pemesanan_sepatu(int buyNum, int jumlah, String warna, String ukuran) throws IOException {
        FileReader file = new FileReader("Daftar_Sepatu.txt");
        BufferedReader fileinput = new BufferedReader(file);
        String data = fileinput.readLine();
        int entryNum = 0;

        while (data != null) {
            entryNum++;

            if (entryNum == buyNum) {
                Scanner datascan = new Scanner(data);
                datascan.useDelimiter(",");
                String namabarang = datascan.next();
                long hargabarang = datascan.nextLong();
                Main.clrscr();
                System.out.println("======================================================");
                System.out.println("===================   PT.SHOPAPP   ===================");
                System.out.println("======================================================");
                System.out.println("Anda Ingin Memesan :");
                System.out.println("\tKategori       : " + sepatu.getJenisBarang());
                System.out.println("\tNama Barang    : " + namabarang);
                System.out.printf ("\tHarga Satuan   : Rp.%-,1d \n", hargabarang);
                System.out.println("\tWarna Pilihan  : " + warna);
                System.out.println("\tUkuran Pilihan : " + ukuran);
                System.out.println("\tJumlah         : " + jumlah);
                long totalharga = jumlah * hargabarang;
                System.out.printf ("\tTotal Harga    : Rp.%-,1d \n", totalharga);
                System.out.println("======================================================");
                boolean isPesan = getYesorNo("Masukkan Ke Keranjang ? (ya/no) : ");
                String dataPemesanan = sepatu.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran+","+jumlah+","+totalharga;
                String cekPesanan = sepatu.getJenisBarang()+","+namabarang+","+hargabarang+","+warna+","+ukuran;
                if (isPesan) {
                    file.close();
                    fileinput.close();
                    boolean isExist = cekIsiKeranjang(cekPesanan);
                    if (isExist) {
                        JumlahkanJumlahdanHarganya(cekPesanan,jumlah,totalharga);
                    } else {
                        masukkanKeKeranjang(dataPemesanan);
                        Main.clrscr();
                        System.out.println("Pesanan Dimasukkan Ke Keranjang Anda");
                    }
                } else {
                    Main.clrscr();
                    System.out.println("Pemesanan Dibatalkan");
                }
                break;
            }


            data = fileinput.readLine();
        }

        file.close();
        fileinput.close();
    }

    /* OPERASI FILE PADA KERANJANG USER */
    private static void masukkanKeKeranjang(String data) throws IOException {
        FileWriter file = new FileWriter("Keranjang_User.txt", true);
        BufferedWriter fileinput = new BufferedWriter(file);

        fileinput.write(data);
        fileinput.newLine();
        fileinput.flush();
        fileinput.close();
        file.close();

    }

    private static boolean cekIsiKeranjang(String datafile) throws IOException {
        FileReader file = new FileReader("Keranjang_User.txt");
        BufferedReader bufferinput = new BufferedReader(file);

        String data = bufferinput.readLine();
        boolean isExist = false;

        while (data != null) {

            if (data.contains(datafile)) {
                isExist = true;
            }

            data = bufferinput.readLine();
        }

        file.close();
        bufferinput.close();

        return isExist;

    }

    private static void JumlahkanJumlahdanHarganya (String data, long jumlahbelanjaan, long totalharga) throws IOException {
        File filepembelian = new File("Keranjang_User.txt");
        FileReader fileinput = new FileReader(filepembelian);
        BufferedReader bufferinput = new BufferedReader(fileinput);

        File Tempfile = new File("TempData.txt");
        FileWriter fileoutput = new FileWriter(Tempfile);
        BufferedWriter writeKeTempfile = new BufferedWriter(fileoutput);

        String datafile = bufferinput.readLine();

        while (datafile != null) {

            if (datafile.contains(data)) {

                Scanner datascan = new Scanner(datafile);
                datascan.useDelimiter(",");
                datascan.next();  datascan.next();  datascan.next();  datascan.next();  datascan.next();
                int jumlahDalamFile = datascan.nextInt();
                int HargaDalamFile = datascan.nextInt();
                long jumlahtotal = jumlahbelanjaan + jumlahDalamFile;
                long hargatotal = totalharga + HargaDalamFile;
                writeKeTempfile.write(data+","+jumlahtotal+","+hargatotal);
                writeKeTempfile.newLine();

            } else {
                writeKeTempfile.write(datafile);
                writeKeTempfile.newLine();
            }

            datafile = bufferinput.readLine();
        }


        writeKeTempfile.flush();


        fileinput.close();
        bufferinput.close();
        fileoutput.close();
        writeKeTempfile.close();

        if (filepembelian.delete()) {
            if (Tempfile.renameTo(filepembelian)) {
                Main.clrscr();
                System.out.println("Barang ini Sudah Dipesan Sebelumnya, Jumlah Ditambahkan");
            } else {
                Main.clrscr();
                System.out.println("Error Rename");
            }
        } else {
            Main.clrscr();
            System.out.println("Error Delete");
        }

    }

    public static void tampilkanpesanan() throws IOException {
        File file = new File("Keranjang_User.txt");
        FileReader readfile = new FileReader(file);
        BufferedReader bufferread = new BufferedReader(readfile);

        int nomor = 0;
        Scanner datascan;

        String data = bufferread.readLine();

        Main.clrscr();
        System.out.println("======================================================");
        System.out.println("===================   PT.SHOPAPP   ===================");
        System.out.println("======================================================");
        System.out.println("Daftar Pemesanan : \n");

        while (data != null) {
            nomor++;
            datascan = new Scanner(data);
            datascan.useDelimiter(",");

            System.out.println("No." + nomor);
            System.out.println("\t  Kategori      : " + datascan.next());
            System.out.println("\t  Nama Barang   : " + datascan.next());
            System.out.printf ("\t  Harga Satuan  : Rp.%-,1d\n", datascan.nextLong());
            System.out.println("\t  Warna         : " + datascan.next());
            System.out.println("\t  Ukuran        : " + datascan.next());
            System.out.println("\t  Jumlah        : " + datascan.next());
            System.out.printf ("\t  Total Harga   : Rp.%-,1d\n", datascan.nextLong());

            data = bufferread.readLine();

        }
        System.out.println("\n======================================================");

        readfile.close();
        bufferread.close();

    }

    public static int cekJumlahpesanan() throws IOException {
        File file = new File("Keranjang_User.txt");
        FileReader fileinput = new FileReader(file);
        BufferedReader bufferinput = new BufferedReader(fileinput);

        String data = bufferinput.readLine();
        int index = 0;

        while (data != null) {
            index++;

            data = bufferinput.readLine();
        }

        fileinput.close();
        bufferinput.close();

        return index;

    }

    private static long get_totalbayar() throws IOException {

        File file = new File("Keranjang_User.txt");
        FileReader fileinput = new FileReader(file);
        BufferedReader bufferfile = new BufferedReader(fileinput);

        long[] harga = new long[20];
        int index = 0;
        Scanner datascan;
        long totalbayar = 0;


        String data = bufferfile.readLine();

        while (data != null) {
            datascan = new Scanner(data);
            datascan.useDelimiter(",");
            datascan.next();
            datascan.next();
            datascan.next();
            datascan.next();
            datascan.next();
            datascan.next();
            harga[index] = datascan.nextLong();

            index++;
            data = bufferfile.readLine();
        }

        for (int i = 0; i < 20; i++) {
            totalbayar += harga[i];
        }

        System.out.printf ("Sub Total Harga : Rp.%-,1d \n", totalbayar);
        System.out.println("Bayaran Belum Termasuk Ongkir, pajak, dll !");
        System.out.println("======================================================");

        fileinput.close();
        bufferfile.close();

        return totalbayar;
    }

    /* HAPUS PESANAN */
    private static int getNum(String message, int limit) {
        Scanner input = new Scanner(System.in);
        int inputan = 0;

        try {
            System.out.print(message);
            inputan = input.nextInt();

            if (inputan < 0 ||inputan > limit) {
                System.out.println("\nWarning : Pesanan Tidak DiTemukan !");
                inputan = getNum(message, limit);
            }

        } catch (InputMismatchException e) {
            System.out.println("\nWarning : Masukkan Inputan Yang valid !");
            inputan = getNum(message, limit);
        }

        return inputan;
    }

    private static void hapuspesanan() throws IOException {
        File daftarpembelian = new File("Keranjang_User.txt");
        FileReader readfile = new FileReader(daftarpembelian);
        BufferedReader bufferread = new BufferedReader(readfile);

        File tempFile = new File("TempFile.txt");
        FileWriter writefile = new FileWriter(tempFile);
        BufferedWriter bufferwrite = new BufferedWriter(writefile);

        //cek jumlah pesanan
        int jumlahpesanan = cekJumlahpesanan();

        //tampilkan pesanan
        tampilkanpesanan();
        System.out.println("                                            0. Batal  ");
        System.out.println("------------------------------------------------------");
        int deleteNum = getNum("Masukkan No.pesanan Yang ingin Dihapus : ", jumlahpesanan);

        int index = 0;
        String data = bufferread.readLine();
        Scanner datascan;

        while (data != null) {
            index++;
            datascan = new Scanner(data);
            datascan.useDelimiter(",");
            boolean isdelete = false;

            if (index == deleteNum) {
                Main.clrscr();
                System.out.println("======================================================");
                System.out.println("===================   PT.SHOPAPP   ===================");
                System.out.println("======================================================");
                System.out.println("Pesanan Yang Ingin Dihapus : ");
                System.out.println("\nNo." + deleteNum);
                System.out.println("\t  Kategori      : " + datascan.next());
                System.out.println("\t  Nama Barang   : " + datascan.next());
                System.out.printf ("\t  Harga Satuan  : Rp.%-,1d\n", datascan.nextLong());
                System.out.println("\t  Warna         : " + datascan.next());
                System.out.println("\t  Ukuran        : " + datascan.next());
                System.out.println("\t  Jumlah        : " + datascan.next());
                System.out.printf ("\t  Total Harga   : Rp.%-,1d\n\n", datascan.nextLong());
                System.out.println("======================================================");
                isdelete = getYesorNo("Ingin Menghapus Pesanan ini ? (ya/no) : ");

                if (isdelete) {
                    //data diskip
                    Main.clrscr();
                    System.out.println("Pesanan Berhasil Dihapus !");
                } else {
                    Main.clrscr();
                    System.out.println("Hapus Pesanan Dibatalkan !");
                    //kita pindahkan seluruh data (selain yang akan didelete) ke file tempPesanan
                    bufferwrite.write(data);
                    bufferwrite.newLine();
                }


            }

            else {
                //kita pindahkan seluruh data (selain yang akan didelete) ke file tempPesanan
                bufferwrite.write(data);
                bufferwrite.newLine();
            }

            data = bufferread.readLine();
        }

        bufferwrite.flush();

        readfile.close();
        bufferread.close();
        writefile.close();
        bufferwrite.close();

        if (deleteNum == 0) {
            Main.clrscr();
            System.out.println("Hapus Pesanan Dibatalkan !");
        }

        //delete file daftarpemesanan
        if(daftarpembelian.delete()) {
            if (tempFile.renameTo(daftarpembelian)) {

            } else {
                System.out.println("Warning : Error !!");
            }
        } else {
            System.out.println("Warning : Error");
        }

    }

    /* EDIT PESANAN */
    private static int getEditNum(String message, int limit) {
        Scanner input = new Scanner(System.in);
        int inputan = 0;

        try {
            System.out.print(message);
            inputan = input.nextInt();

            if (inputan < 0 ||inputan > limit) {
                System.out.println("\nWarning : Opsi Editan Tidak Tersedia !");
                inputan = getEditNum(message, limit);
            }

        } catch (InputMismatchException e) {
            System.out.println("\nWarning : Masukkan Inputan Yang valid !");
            inputan = getEditNum(message,limit);
        }

        return inputan;
    }

    private static void editpesanan() throws IOException {

        String[] daftarwarna = {"kosong", "Hitam", "Abu-abu", "Merah", "Putih","Cokelat"};

        String[] daftarukuran = {"kosong", "S", "M", "L", "XL", "XXL"};
        String[] daftarukuransepatu = {"kosong", "39", "40", "41", "42", "43", "44"};

        File filepembelian = new File("Keranjang_User.txt");
        FileReader fileinput = new FileReader(filepembelian);
        BufferedReader bufferinput = new BufferedReader(fileinput);

        File Tempfile = new File("Tempfile.txt");
        FileWriter fileoutput = new FileWriter(Tempfile);
        BufferedWriter writekeTempfile = new BufferedWriter(fileoutput);

        int jumlahpesanan = cekJumlahpesanan();

        tampilkanpesanan();
        System.out.println("                                             0. Batal ");
        System.out.println("------------------------------------------------------");
        int editNum = getNum("Masukkan No.pesanan Yang ingin Diedit : ", jumlahpesanan);

        Scanner datascan;
        String data = bufferinput.readLine();
        int entrycount = 0;

        while (data != null) {
            entrycount++;
            boolean isEdit = false;

            if (entrycount == editNum) {
                datascan = new Scanner(data);
                datascan.useDelimiter(",");

                String jenis = datascan.next();
                String namabarang = datascan.next();
                int hargasatuan = datascan.nextInt();
                String warna = datascan.next();
                String ukuran = datascan.next();
                int jumlah = datascan.nextInt();
                int totalharga = datascan.nextInt();
                Main.clrscr();

                System.out.println("======================================================");
                System.out.println("===================   PT.SHOPAPP   ===================");
                System.out.println("======================================================");
                System.out.println("Pesanan Yang Ingin Diedit : ");
                System.out.println("\nNo." + editNum);
                System.out.println("\t  Jenis         : " + jenis);
                System.out.println("\t  Nama Barang   : " + namabarang);
                System.out.printf ("\t  Harga Satuan  : Rp.%-,1d\n", hargasatuan);
                System.out.println("\t  Warna         : " + warna);
                System.out.println("\t  Ukuran        : " + ukuran);
                System.out.println("\t  Jumlah        : " + jumlah);
                System.out.printf ("\t  Total Harga   : Rp.%-,1d\n\n", totalharga);
                System.out.print  ("======================================================\n");
                isEdit = getYesorNo("Ingin Mengedit Pesanan ini ? (ya/no) : ");

                if (isEdit) {
                    System.out.println("======================================================");
                    System.out.println("  1. Warna             2. Ukuran  ");
                    System.out.println("  3. Jumlah            0. Batal");
                    System.out.println("------------------------------------------------------");
                    int pilihanubah = getEditNum("Masukkan Pilihan Yang ingin Diubah : ", 3);

                    //edit warna
                    if (pilihanubah == 1) {
                        daftarwarna();
                        System.out.println("Warna Sebelumnya : " + warna);
                        System.out.println("------------------------------------------------------");
                        int warnabaru = getindexwarna(5);
                        String datafile = jenis+","+namabarang+","+hargasatuan+","+daftarwarna[warnabaru]+","+ukuran;
                        boolean isExist = cekIsiKeranjang(datafile);
                        if (isExist) {
                            Main.clrscr();
                            System.out.println("Barang Yang Anda Inginkan sudah ada Di Keranjang Anda");
                            System.out.println("Edit Gagal !");
                            writekeTempfile.write(data);
                            writekeTempfile.newLine();
                        } else {
                            writekeTempfile.write(jenis+","+namabarang+","+hargasatuan+","+daftarwarna[warnabaru]+","+ukuran+","+jumlah+","+totalharga);
                            writekeTempfile.newLine();
                            Main.clrscr();
                            System.out.println("Edit Warna Pesanan No." + editNum + " Berhasil !");
                        }

                    }

                    //edit ukuran
                    else if (pilihanubah == 2) {
                        if (jenis.equalsIgnoreCase("Sepatu")){
                            Main.clrscr();
                            System.out.println("======================================================");
                            System.out.println("===================   PT.SHOPAPP   ===================");
                            daftarukuransepatu();
                            System.out.println("Ukuran Sebelumnya : " + ukuran);
                            System.out.println("------------------------------------------------------");
                            int ukuranbaru = getindexukuran(6);
                            String datafile = jenis+","+namabarang+","+hargasatuan+","+warna+","+daftarukuransepatu[ukuranbaru];
                            boolean isExist = cekIsiKeranjang(datafile);
                            if (isExist) {
                                Main.clrscr();
                                System.out.println("Barang Yang Anda Inginkan sudah ada Di Keranjang Anda");
                                System.out.println("Edit Gagal !");
                                writekeTempfile.write(data);
                                writekeTempfile.newLine();
                            } else {
                                writekeTempfile.write(jenis+","+namabarang+","+hargasatuan+","+warna+","+daftarukuransepatu[ukuranbaru]+","+jumlah+","+totalharga);
                                writekeTempfile.newLine();
                                Main.clrscr();
                                System.out.println("Edit Ukuran Pesanan No." + editNum + " Berhasil !");
                            }

                        } else if (jenis.equalsIgnoreCase("Jam Tangan")) {
                            Main.clrscr();
                            System.out.println("Warning : Ukuran Jam Tangan Tidak Bisa Diubah");
                            writekeTempfile.write(data);
                            writekeTempfile.newLine();
                        } else {
                            Main.clrscr();
                            System.out.println("======================================================");
                            System.out.println("===================   PT.SHOPAPP   ===================");
                            daftarukuran();
                            System.out.println("Ukuran Sebelumnya : " + ukuran);
                            System.out.println("------------------------------------------------------");
                            int ukuranbaru = getindexukuran(5);
                            String datafile = jenis+","+namabarang+","+hargasatuan+","+warna+","+daftarukuran[ukuranbaru];
                            boolean isExist = cekIsiKeranjang(datafile);
                            if (isExist) {
                                Main.clrscr();
                                System.out.println("Barang Yang Anda Inginkan sudah ada Di Keranjang Anda");
                                System.out.println("Edit Gagal !");
                                writekeTempfile.write(data);
                                writekeTempfile.newLine();
                            } else {
                                writekeTempfile.write(jenis+","+namabarang+","+hargasatuan+","+warna+","+daftarukuran[ukuranbaru]+","+jumlah+","+totalharga);
                                writekeTempfile.newLine();
                                Main.clrscr();
                                System.out.println("Edit Ukuran Pesanan No." + editNum + " Berhasil !");
                            }
                        }
                    }

                    //edit jumlah
                    else if (pilihanubah == 3) {
                        System.out.println("======================================================");
                        System.out.println("Jumlah Sebelumnya : " + jumlah);
                        System.out.println("------------------------------------------------------");
                        int jumlahbaru = getAmount();
                        long totalhargabaru = jumlahbaru * hargasatuan;
                        writekeTempfile.write(jenis+","+namabarang+","+hargasatuan+","+warna+","+ukuran+","+jumlahbaru+","+totalhargabaru);
                        writekeTempfile.newLine();
                        Main.clrscr();
                        System.out.println("Edit Jumlah Pesanan No." + editNum + " Berhasil !");
                    }

                    //batal
                    else {
                        Main.clrscr();
                        System.out.println("Edit Pesanan Dibatalkan !");
                        writekeTempfile.write(data);
                        writekeTempfile.newLine();
                    }

                } else {
                    Main.clrscr();
                    System.out.println("Edit Pesanan Dibatalkan !");
                    //jika tidak jadi mengedit, masukkan ke tempfile
                    writekeTempfile.write(data);
                    writekeTempfile.newLine();
                }

            } else {
                //data selain yang diedit dimasukkan ke tempfile
                writekeTempfile.write(data);
                writekeTempfile.newLine();
            }

            data = bufferinput.readLine();
        }

        writekeTempfile.flush();

        fileinput.close();
        bufferinput.close();
        fileoutput.close();
        writekeTempfile.close();

        if (editNum == 0) {
            Main.clrscr();
            System.out.println("Edit Pesanan Dibatalkan !");
        }

        if (filepembelian.delete()) {
            if (Tempfile.renameTo(filepembelian)) {

            } else {
                System.out.println("error !!");
            }
        } else {
            System.out.println("error !");
        }


    }

    /* DATA USER */
    private static void tampilkanaData() throws IOException {

        FileReader file = new FileReader("Email&Password_User.txt");
        BufferedReader ReadFile = new BufferedReader(file);

        FileReader fileemail = new FileReader("EmailUser.txt");
        BufferedReader bufferinput = new BufferedReader(fileemail);

        String email = bufferinput.readLine();

        String data = ReadFile.readLine();

        while (data != null) {
            Scanner datascan = new Scanner(data);
            datascan.useDelimiter("/");
            String emailUser = datascan.next();

            if (emailUser.equals(email)) {
                Scanner scanner = new Scanner(data);
                scanner.useDelimiter("_");
                scanner.next(); //email&password user
                String datauser = scanner.next();
                scanner = new Scanner(datauser);
                scanner.useDelimiter(",");
                String nama = scanner.next();
                String noHP = scanner.next();
                String kota = scanner.next();
                String prov = scanner.next();
                System.out.println("======================================================");
                System.out.println("===================   PT.SHOPAPP   ===================");
                System.out.println("======================================================");
                System.out.println("Data Anda   :");
                System.out.println("\t1. Nama     : " + nama);
                System.out.println("\t2. Nomor Hp : " + noHP);
                System.out.println();
                System.out.println("Alamat Anda :");
                System.out.println("\t3. Kota     : " + kota);
                System.out.println("\t4. Provinsi : " + prov);
                System.out.println("------------------------------------------------------");
                System.out.println("                                   9. Ganti Password");
                System.out.println("                                   0. Batal         ");
                System.out.println("------------------------------------------------------");
                break;
            }

            data = ReadFile.readLine();
        }

        file.close();
        ReadFile.close();
        fileemail.close();
        bufferinput.close();


    }

    private static void editData() throws IOException {
        Scanner input = new Scanner(System.in);

        File database = new File("Email&Password_User.txt");
        FileReader file = new FileReader(database);
        BufferedReader bufferinput = new BufferedReader(file);

        File temp = new File("temptes.txt");
        FileWriter tempfile = new FileWriter(temp);
        BufferedWriter writekeTemp = new BufferedWriter(tempfile);

        FileReader fileuser = new FileReader("EmailUser.txt");
        BufferedReader getEmailuser = new BufferedReader(fileuser);
        String email = getEmailuser.readLine(); // email yang login

        String data = bufferinput.readLine();

        if (email.equalsIgnoreCase("Admin")) {
            file.close();
            bufferinput.close();
            fileuser.close();
            getEmailuser.close();
            tempfile.close();
            writekeTemp.close();
            if (temp.delete()) {
                Main.clrscr();
                System.out.println("Data Admin Tidak Bisa Diedit !");
                return;
            } else {
                Main.clrscr();
                System.out.println("Error");
                return;
            }

        }

        else {
            while (data != null)  {
                Scanner datascan = new Scanner(data);
                datascan.useDelimiter("/");
                String emailuser = datascan.next();

                if (emailuser.equals(email)) {

                    Scanner datascan2 = new Scanner(data);
                    datascan2.useDelimiter("_");
                    String akun = datascan2.next(); // email&password user
                    String datauser = datascan2.next();
                    datascan2 = new Scanner(datauser);
                    datascan2.useDelimiter(",");
                    String namauser = datascan2.next();
                    String NoHpUser = datascan2.next();
                    String kotauser = datascan2.next();
                    String provuser = datascan2.next();

                    tampilkanaData();
                    Edit:
                    do {
                        System.out.print("Masukkan Nomor Data yang Ingin Diedit : ");
                        String numEdit = input.nextLine();
                        //edit nama
                        if (numEdit.equals("1")) {
                            System.out.println("------------------------------------------------------");
                            System.out.println("Data Yang Ingin Diedit ");
                            System.out.println("Nama : " + namauser);
                            System.out.println("------------------------------------------------------");
                            String namabaru = Main.getnama("Masukkan Nama Baru Anda : ");
                            writekeTemp.write(akun+"_"+namabaru+","+NoHpUser+","+kotauser+","+provuser);
                            writekeTemp.newLine();
                            Main.clrscr();
                            System.out.println("Edit Nama Berhasil !");
                            pembayaran.writeAktivitasUser("Merubah Nama : " + namauser + " --> " + namabaru);
                            break Edit;
                        }

                        //edit nomor hp
                        else if (numEdit.equals("2")) {
                            System.out.println("------------------------------------------------------");
                            System.out.println("Data Yang Ingin Diedit ");
                            System.out.println("Nomor Hp : " + NoHpUser);
                            System.out.println("------------------------------------------------------");
                            long nomorhpbaru = Main.getnomorhp("Masukkan Nomor Hp Baru Anda : +628 ");
                            writekeTemp.write(akun+"_"+namauser+","+"+628"+nomorhpbaru+","+kotauser+","+provuser);
                            writekeTemp.newLine();
                            Main.clrscr();
                            System.out.println("Edit Nomor Hp Berhasil !");
                            pembayaran.writeAktivitasUser("Merubah Nomor Hp : " + NoHpUser + " --> " + nomorhpbaru);
                            break Edit;
                        }

                        //edit kota
                        else if (numEdit.equals("3")) {
                            System.out.println("------------------------------------------------------");
                            System.out.println("Data Yang Ingin Diedit ");
                            System.out.println("Kota : " + kotauser);
                            System.out.println("------------------------------------------------------");
                            String kotabaru = Main.getKota("Masukkan Kota Baru Anda : ");
                            writekeTemp.write(akun+"_"+namauser+","+NoHpUser+","+kotabaru+","+provuser);
                            writekeTemp.newLine();
                            Main.clrscr();
                            System.out.println("Edit Kota Berhasil !");
                            pembayaran.writeAktivitasUser("Merubah Kota : " + kotauser + " --> " + kotabaru);
                            break Edit;
                        }

                        //edit provinsi
                        else if (numEdit.equals("4")) {
                            System.out.println("------------------------------------------------------");
                            System.out.println("Data Yang Ingin Diedit ");
                            System.out.println("Provinsi : " + provuser);
                            System.out.println("------------------------------------------------------");
                            String provbaru = Main.getProvinsi("Masukkan Provinsi Baru Anda : ");
                            writekeTemp.write(akun+"_"+namauser+","+NoHpUser+","+kotauser+","+provbaru);
                            writekeTemp.newLine();
                            Main.clrscr();
                            System.out.println("Edit Provinsi Berhasil !");
                            pembayaran.writeAktivitasUser("Merubah Provinsi : " + provuser + " --> " + provbaru);
                            break Edit;
                        }

                        //batal mengedit
                        else if (numEdit.equals("0")) {
                            Main.clrscr();
                            System.out.println("Edit Profil dibatalkan");
                            writekeTemp.write(data);
                            writekeTemp.newLine();
                            break Edit;
                        }

                        //edit password
                        else if (numEdit.equals("9")) {
                            Main.clrscr();
                            String dataubahan = gantiPassword();
                            writekeTemp.write(dataubahan);
                            writekeTemp.newLine();
                            break Edit;
                        }

                        else {
                            System.out.println("Opsi Tidak Temukan !");
                        }
                    } while (true);

                }

                else {
                    writekeTemp.write(data);
                    writekeTemp.newLine();
                }

                data = bufferinput.readLine();
            }
            writekeTemp.flush();

            file.close();
            bufferinput.close();
            fileuser.close();
            getEmailuser.close();
            tempfile.close();
            writekeTemp.close();

            if (database.delete()) {
                if(temp.renameTo(database)) {

                } else {
                    System.out.println("Error ubah");
                }
            } else {
                System.out.println("Error delete");
            }
        }


    }

    /* WARNA */
    private static String getWarna() {
        String[] warna = {"kosong", "Hitam", "Abu-abu", "Merah", "Putih","Cokelat"};
        daftarwarna();
        int index = getindexwarna(warna.length-1);

        return warna[index];
    }

    private static int getindexwarna(int limit) {
        Scanner input = new Scanner(System.in);
        int jumlah;

        try {
            System.out.print("Masukkan Warna Pilihan Anda : ");
            jumlah = input.nextInt();
            if (jumlah > limit || jumlah == 0) {
                System.err.println("Warning : Masukkan Inputan yang valid ! ");
                jumlah = getindexwarna(limit);
            }
        } catch (InputMismatchException ex) {
            System.err.println("Warning : Masukkan Inputan yang valid ! ");
            jumlah = getindexwarna(limit);
        }

        return jumlah;
    }

    private static void daftarwarna() {
        Main.clrscr();
        System.out.println("======================================================");
        System.out.println("===================   PT.SHOPAPP   ===================");
        System.out.println("======================================================");
        System.out.println("Warna Yang Tersedia : ");
        System.out.println("\t1. Hitam ");
        System.out.println("\t2. Abu-abu");
        System.out.println("\t3. Merah");
        System.out.println("\t4. Putih");
        System.out.println("\t5. Cokelat");
        System.out.println("======================================================");
    }

    /* UKURAN */
    private static String getUkuran() {
        String[] ukuran = {"kosong", "S", "M", "L", "XL", "XXL"};
        daftarukuran();
        int index = getindexukuran(ukuran.length - 1);

        return ukuran[index];
    }

    private static void daftarukuran() {
        System.out.println("======================================================");
        System.out.println("Daftar Ukuran Yang Tersedia :");
        System.out.println("\t1. S");
        System.out.println("\t2. M");
        System.out.println("\t3. L");
        System.out.println("\t4. XL");
        System.out.println("\t5. XXL");
        System.out.println("======================================================");
    }

    private static String getUkuranSepatu() {
        String[] ukuransepatu = {"kosong", "39", "40", "41", "42", "43", "44"};
        daftarukuransepatu();
        int index = getindexukuran(ukuransepatu.length - 1);

        return ukuransepatu[index];
    }

    private static void daftarukuransepatu() {
        System.out.println("======================================================");
        System.out.println("Daftar Ukuran Yang Tersedia :");
        System.out.println("\t1. 39");
        System.out.println("\t2. 40");
        System.out.println("\t3. 41");
        System.out.println("\t4. 42");
        System.out.println("\t5. 43");
        System.out.println("\t6. 44");
        System.out.println("======================================================");
    }

    private static int getindexukuran(int limit) {
        Scanner input = new Scanner(System.in);
        int jumlah;

        try {
            System.out.print("Masukkan Ukuran Pilihan Anda : ");
            jumlah = input.nextInt();
            if (jumlah > limit || jumlah == 0) {
                System.err.println("Warning : Masukkan Inputan yang valid ! ");
                jumlah = getindexukuran(limit);
            }
        } catch (InputMismatchException ex) {
            System.err.println("Warning : Masukkan Inputan yang valid ! ");
            jumlah = getindexukuran(limit);
        }

        return jumlah;
    }

    /* GANTI PASSWORD */
    private static String gantiPassword() throws IOException {
        String dataubahan = "";

        File database = new File("Email&Password_User.txt");
        FileReader file = new FileReader(database);
        BufferedReader bufferinput = new BufferedReader(file);

        FileReader fileuser = new FileReader("EmailUser.txt");
        BufferedReader getEmailuser = new BufferedReader(fileuser);
        String emailyanglogin = getEmailuser.readLine(); // email yang login

        String data = bufferinput.readLine();

        while (data != null) {
            Scanner datascan = new Scanner(data);
            datascan.useDelimiter("/");
            String email = datascan.next();

            if (email.equals(emailyanglogin)) {
                Scanner datascan2 = new Scanner(data);
                datascan2.useDelimiter("_");
                String email0password = datascan2.next();
                String datauser = datascan2.next();
                datascan2 = new Scanner(email0password);
                datascan2.useDelimiter("/");
                String emailuser = datascan2.next();
                String passworduser = datascan2.next();

                System.out.println("======================================================");
                System.out.println("===================   PT.SHOPAPP   ===================");
                System.out.println("======================================================");
                String passwordlama = Main.getPassword("Masukkan Password Lama Anda : ");
                String passwordbaru = Main.getPassword("Masukkan Paswword Baru Anda : ");

                if (passwordlama.equals(passworduser)) {

                    boolean isEdit = getYesorNo("Konfirmasi ? (ya/no) : ");
                    if (isEdit) {
                        Main.clrscr();
                        System.out.println("Password Anda Berhasil Diubah");
                        dataubahan = String.format("%s/%s_%s", emailuser,passwordbaru,datauser);
                    } else {
                        Main.clrscr();
                        System.out.println("Batal Mengubah Password");
                        dataubahan = String.format("%s/%s_%s", emailuser,passworduser,datauser);
                    }

                } else {
                    Main.clrscr();
                    System.out.println("Warning : Gagal Mengubah Password !");
                    System.out.println("Password Lama Yang Anda Masukkan Salah");
                    dataubahan = String.format("%s/%s_%s", emailuser,passworduser,datauser);
                }
            }


            data = bufferinput.readLine();
        }


        file.close();
        bufferinput.close();
        fileuser.close();
        getEmailuser.close();

        return dataubahan;

    }

    /* utility */
    private static boolean readriwayatuser() throws IOException {
        Scanner input = new Scanner(System.in);
        boolean lanjut = true;
        FileReader file = new FileReader("EmailUser.txt");
        BufferedReader fileemail = new BufferedReader(file);
        String emailyangLogin = fileemail.readLine();

        String filedata = "dataRiwayat_" + emailyangLogin + ".txt";

        try {
            File RiwayatUser = new File(filedata);
            FileReader fileread = new FileReader(RiwayatUser);
            BufferedReader br = new BufferedReader(fileread);
            String data = br.readLine();
            if (data == null) {
                Main.clrscr();
                System.out.println("Riwayat Transaksi Anda Kosong !");
            } else {
                Main.clrscr();
                System.out.println("=====================================================================");
                System.out.println("                       RIWAYAT TRANSAKSI ANDA                        ");
                System.out.println("                       User : " + emailyangLogin);
                System.out.println("=====================================================================");
                while (data != null) {
                    System.out.println(data);
                    data = br.readLine();
                }
                System.out.println("1. Hapus Riwayat Transaksi Saya");
                System.out.println("2. Back");
                System.out.println("3. Exit");
                do {
                    System.out.print  ("Masukkan Pilihan Anda : ");
                    String pilihan = input.next();
                    if (pilihan.equals("1")) {
                        file.close();
                        fileemail.close();
                        fileread.close();
                        br.close();
                        if (RiwayatUser.delete()) {
                            Main.clrscr();
                            System.out.println("Riwayat Transaksi Berhasil Dihapus");
                            lanjut = true;
                        } else {
                            Main.clrscr();
                            System.out.println("Error");
                            lanjut = true;
                        }
                        break;
                    }
                    else if (pilihan.equals("2")) {
                        Main.clrscr();
                        lanjut = true;
                        break;
                    } else if (pilihan.equals("3")) {
                        lanjut = false;
                        break;
                    } else {
                        System.out.println("Warning : Inputan Tidak Valid !");
                    }
                } while (true);

            }
            file.close();
            fileemail.close();
            fileread.close();
            br.close();

        } catch (Exception e) {
            Main.clrscr();
            System.out.println("Riwayat Transaksi Anda Kosong !");
        }

        file.close();
        fileemail.close();

        return lanjut;
    }

    public static boolean getYesorNo(String message) {
        Scanner input = new Scanner(System.in);
        String pilihan;
        do {
            System.out.print(message);
            pilihan = input.next();
            if ( (!(pilihan.equals("ya") || pilihan.equals("no"))) ) {
                System.out.println("inputan tidak valid");
            }
        } while (!(pilihan.equals("ya") || pilihan.equals("no")));

        return pilihan.equalsIgnoreCase("ya");
    }

    private static int getAmount() {
        Scanner input = new Scanner(System.in);
        int jumlah;

        try {
            System.out.print("Masukkan Jumlah : ");
            jumlah = input.nextInt();

            if(jumlah == 0) {
                System.err.println("Warning : Masukkan Inputan yang valid ! ");
                jumlah = getAmount();
            }
        } catch (InputMismatchException ex) {
            System.err.println("Warning : Masukkan Inputan yang valid ! ");
            jumlah = getAmount();
        }

        return jumlah;
    }

    private static int getNumPilihan(int limit) {
        Scanner input = new Scanner(System.in);
        int num;

        try {
            System.out.print("Masukkan Pilihan Anda : ");
            num = input.nextInt();

            if (num < 0 || num > limit) {
                System.out.println("Pilihan Tidak Tersedia !");
                num = getNumPilihan(limit);
            } else {

            }

        } catch (InputMismatchException e) {
            System.out.println("Masukkan Inputan Yang Valid !");
            num = getNumPilihan(limit);
        }

        return num;
    }

}
