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

public class Main{
    public static void main(String[] args) throws IOException {
        barang.main();
        Start();
    }

    public static void Start() throws IOException {
        boolean loop = true;

        Scanner input = new Scanner(System.in);
        String pilihan, credit, email,password, exit;
        clrscr();

        LOOP:
        do {
            System.out.println("======================================================");
            System.out.println("===================     WELCOME    ===================");
            System.out.println("======================================================");
            System.out.println("               Login Jika Ingin Belanja               ");
            System.out.println("             Sign Up Jika Belum Punya Akun            ");
            System.out.println("------------------------------------------------------");
            System.out.println("Opsi Pilihan :                        ");
            System.out.println("\t1. Login                            ");
            System.out.println("\t2. Sign Up                          ");
            System.out.println("======================================================");
            System.out.println("Opsi Lainnya :                        ");
            System.out.println("\t3. Admin                            ");
            System.out.println("\t4. Profil                           ");
            System.out.println("\t5. Exit                             ");
            System.out.println("======================================================");
            System.out.print  ("Masukkan Pilihan Anda : ");
            pilihan = input.next();

            //login
            if (pilihan.equals("1")) {
                boolean isGagal = login();
                if (isGagal) {
                    continue LOOP;
                } else {
                    shopping.main();
                    break LOOP;
                }
            }

            //sign up
            else if (pilihan.equals("2")) {
                clrscr();
                signUp();
                continue LOOP;
            }

            //admin
            else if (pilihan.equals("3")) {
                clrscr();
                do {
                    admin();

                    System.out.print  (" Email    : ");
                    email = input.next();
                    if (email.equals("back")) {
                        clrscr();
                        continue LOOP;
                    }

                    System.out.print  (" Password : ");
                    password = input.next();
                    if (password.equals("back")) {
                        clrscr();
                        continue LOOP;
                    }

                    if (!email.equals("admin") && !password.equals("admin")) {
                        clrscr();
                        System.err.println("Warning : Gagal Login");
                    } else if (!email.equals("admin")) {
                        clrscr();
                        System.err.println("Warning : Email Salah");
                    } else if (!password.equals("admin")) {
                        clrscr();
                        System.err.println("Warning : Password Salah");
                    } else {
                        clrscr();
                        System.out.println("Welcome Admin !");
                        boolean isLanjut = tampilanadmin();
                        if (isLanjut) {
                            clrscr();
                            continue LOOP;
                        }  else {
                            canceled();
                            break LOOP;
                        }
                    }

                } while (!email.equals("admin") || !password.equals("admin"));
            }

            //profil
            else if (pilihan.equals("4")) {

                profil();
                System.out.println("               1. Back    |    2. Exit               ");
                System.out.println("------------------------------------------------------");
                do {
                    System.out.print("Masukkan Pilihan Anda : ");
                    credit = input.next();
                    if (credit.equals("1")) {
                        clrscr();
                        continue LOOP;
                    } else if (credit.equals("2")) {
                        canceled();
                        break LOOP;
                    } else {
                        System.out.println("Warning : Masukkan Inputan Yang Valid !");
                    }
                } while (!credit.equals("1") || !credit.equals("2"));

            }

            //exit
            else if (pilihan.equals("5")) {
                clrscr();
                do {
                    System.out.println("======================================================");
                    System.out.println("===================   PT.SHOPAPP   ===================");
                    System.out.println("======================================================");
                    System.out.print  ("Keluar Dari Program ? (ya/no) : ");
                    exit = input.next();
                    if (exit.equals("ya")) {
                        canceled();
                        break LOOP;
                    } else if (exit.equals("no")) {
                        clrscr();
                        continue LOOP;
                    } else {
                        clrscr();
                        System.out.println("Warning : Masukkan Inputan Yang Valid !");
                    }
                } while ( !(exit.equals("ya") || exit.equals("no")) );

            }

            else {
                clrscr();
                System.out.println("Warning : Masukkan Inputan Yang Valid !");
            }

        } while (loop);
    }

    /* LOGIN */
    private static boolean login() throws IOException {
        try {
            FileReader fileinput = new FileReader("Email&Password_User.txt");
            fileinput.close();

        } catch (Exception e) {
            FileWriter fileoutput = new FileWriter("Email&Password_User.txt");
            fileoutput.close();
        }

        FileReader fileinput = new FileReader("Email&Password_User.txt");
        BufferedReader getAccount = new BufferedReader(fileinput);

        Scanner datascan;
        String password = "";

        boolean gagal = false;
        boolean loop = true;

        clrscr();
        System.out.println("======================================================");
        System.out.println("===================      LOGIN     ===================");
        System.out.println("======================================================");
        System.out.println("            Type 'back' in Email or Password          ");
        System.out.println("                 if you want to go back               ");
        System.out.println("------------------------------------------------------");
        System.out.println("         Sign Up if you Don't have any Account ");
        System.out.println("------------------------------------------------------");
        String email = getEmail(false,true);

        if (email.equalsIgnoreCase("back")) {
            loop = false;
        }
        else {
            password = getPassword("Password : ");
            if (password.equalsIgnoreCase("back")) {
                loop = false;
            }
        }

        String email0pw = email+"/"+password;

        String account = getAccount.readLine();
        String akunuser = "";

        while (account != null && loop == true) {

            datascan = new Scanner(account);
            datascan.useDelimiter("_");
            akunuser = datascan.next();
            if (akunuser.equals(email0pw)) {
                gagal = false;
                clrscr();
                GetUserEmail(email);
                String username = getUsername();
                System.out.println("Welcome, " + username + ". Happy Shopping :)");
                break;
            }

            account = getAccount.readLine();
        }

        try {

            if (email.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
                clrscr();
                GetUserEmail("Admin");
                System.out.println("Welcome Admin, Happy Shopping");
                gagal = false;
            }

            else if (email.equalsIgnoreCase("back") || password.equalsIgnoreCase("back")) {
                clrscr();
                gagal = true;
            }

            else if (!akunuser.equals(email0pw)) {
                gagal = true;
                clrscr();
                System.out.println("Login Gagal !");
                System.out.println("Sign Up jika Anda Belum Memiliki Akun");
            }
        } catch (Exception e) {
            gagal = true;
            clrscr();
            System.out.println("Login Gagal !");
            System.out.println("Sign Up jika Anda Belum Memiliki Akun");
        }

        fileinput.close();
        getAccount.close();

        return gagal;
    }

    private static String getUsername() throws IOException {
        String username = "";
        FileReader fileemail = new FileReader("EmailUser.txt");
        BufferedReader bufferinput = new BufferedReader(fileemail);
        String emailyangLogin = bufferinput.readLine();

        FileReader fileDatabase = new FileReader("Email&Password_User.txt");
        BufferedReader bufferDatabase = new BufferedReader(fileDatabase);
        String data = bufferDatabase.readLine();

        while (data != null) {
            Scanner datascan = new Scanner(data);
            datascan.useDelimiter("/");
            String emailuser = datascan.next();

            if (emailuser.equals(emailyangLogin)) {
                Scanner datascan2 = new Scanner(data);
                datascan2.useDelimiter("_");
                datascan2.next();
                String datauser = datascan2.next();
                datascan2 = new Scanner(datauser);
                datascan2.useDelimiter(",");
                username = datascan2.next();
                break;
            }

            data = bufferDatabase.readLine();
        }

        fileemail.close();
        bufferinput.close();
        fileDatabase.close();
        bufferDatabase.close();

        return username;
    }

    /* SIGN UP */
    private static void signUp() throws IOException {

        FileWriter fileoutput = new FileWriter("Email&Password_User.txt", true);
        BufferedWriter CreateAccount = new BufferedWriter(fileoutput);

        System.out.println("======================================================");
        System.out.println("=====================   Sign Up   ====================");
        System.out.println("======================================================");
        System.out.println("           Type 'back' in Email or Password           ");
        System.out.println("                 if you want to go back               ");
        System.out.println("------------------------------------------------------");
        System.out.println("                  Create Your Account                 ");
        System.out.println("------------------------------------------------------");
        String email = getEmail(true, false);
        if (email.equalsIgnoreCase("back")) {
            fileoutput.close();
            CreateAccount.close();
            clrscr();
            return;
        }
        String password = getPassword("Password : ");
        if (password.equalsIgnoreCase("back")) {
            fileoutput.close();
            CreateAccount.close();
            clrscr();
            return;
        }

        clrscr();
        System.out.println("======================================================");
        System.out.println("=====================   Sign Up   ====================");
        System.out.println("======================================================");
        System.out.println("           Type 'back' in Email or Password           ");
        System.out.println("                 if you want to go back               ");
        System.out.println("------------------------------------------------------");
        System.out.println("                  Create Your Account                 ");
        System.out.println("------------------------------------------------------");
        System.out.println("Email Anda    : " + email);
        System.out.println("Password Anda : " + password);
        boolean isCreate = shopping.getYesorNo("Daftar ? (ya/no) : ");
        if (isCreate) {
            clrscr();
            String datauser = createDatauser();
            CreateAccount.write(email + "/" + password + "_" + datauser);
            CreateAccount.newLine();
            CreateAccount.flush();
            writeAktivitasUserBaru(email + " Baru Saja Bergabung");
        } else {
            clrscr();
            System.out.println("Sign up Failed !");
        }


        fileoutput.close();
        CreateAccount.close();
    }

    public static String getEmail(boolean cekemail, boolean getadmin) throws IOException {
        Scanner input = new Scanner(System.in);

        FileReader fileinput = new FileReader("Email&Password_User.txt");
        BufferedReader readAccount = new BufferedReader(fileinput);
        String dataAccount = readAccount.readLine();

        String emailYangTelahTerdaftar;
        String email;

        System.out.print ("Email    : ");
        email = input.next();

        if (cekemail) {
            while ( dataAccount != null) {
                Scanner datascan = new Scanner(dataAccount);
                datascan.useDelimiter("/");
                emailYangTelahTerdaftar = datascan.next();
                if (email.equalsIgnoreCase(emailYangTelahTerdaftar)) {
                    System.out.println("Warning : Email Telah Terdaftar !");
                    email = getEmail(cekemail,getadmin);
                    break;
                }
                //cek email digunakan di sign up, jika email yang dimasukkan sudah terdapat dalam database,
                //maka email tersebut tidak bisa didaftarkan lagi. cekemail = true
                //di login, cek email tidak gunakan, cekemail = false
                dataAccount = readAccount.readLine();
            }
        }

        if (email.equalsIgnoreCase("admin")) {
            if (getadmin) {
                //admin bisa dimasukkan saat login, tetapi di sign up tidak bisa
            } else {
                System.out.println("Warning : Format Email tidak Valid !");
                email = getEmail(cekemail,getadmin);
            }
        }
        else if (email.contains("_") || email.contains(",") || email.contains("/")) {
            System.out.println("Warning : Format Email tidak Valid !");
            email = getEmail(cekemail,getadmin);
        }
        else if (email.equalsIgnoreCase("back")) {
            //jika user ingin kembali ke halaman sebelumnya
        }
        else if (email.equalsIgnoreCase("@gmail.com") || email.equalsIgnoreCase("@gmail.co.id")
                || email.equalsIgnoreCase("@yahoo.com") || email.equalsIgnoreCase("@yahoo.co.id") || email.equalsIgnoreCase("@user.com")) {
            System.out.println("Warning : Format Email tidak Valid !");
            email = getEmail(cekemail,getadmin);
            //jika user hanya memasukkan @blablabla, maka format email tidak valid
        }
        else if (email.contains("@gmail.com") || email.contains("@gmail.co.id") || email.contains("@yahoo.com") || email.contains("@yahoo.co.id") || email.contains("@user.com")) {
            //email valid jika ****@****.com
            //jika valid sign up berhasil
        } else {
            System.out.println("Warning : Format Email tidak Valid !");
            email = getEmail(cekemail,getadmin);
        }

        fileinput.close();
        readAccount.close();

        return email;
    }

    public static String getPassword(String message) {
        Scanner input = new Scanner(System.in);

        System.out.print(message);
        String password = input.next();
        if (password.equalsIgnoreCase("admin")) {

        } else if (password.equalsIgnoreCase("back")) {

        } else if (password.contains("_") || password.contains(",") || password.contains("/")) {
            System.out.println("Warning : Password Hanya Boleh Terdiri dari Huruf/Angka !");
            password = getPassword(message);
        } else if (password.length() < 6) {
            System.out.println("Warning : Password Harus Lebih Dari 6 Huruf/Angka !");
            password = getPassword(message);
        }
        return password;
    }

    /* ADMIN */
    private static void admin() {
        System.out.println("======================================================");
        System.out.println("===================      ADMIN     ===================");
        System.out.println("======================================================");
        System.out.println("           Type 'back' in Email or Password           ");
        System.out.println("                if you want to go back                ");
        System.out.println("------------------------------------------------------");

    }

    private static boolean tampilanadmin() throws IOException {
        Scanner input = new Scanner(System.in);
        boolean lanjut = true;
        int jumlahuser = jumlahUser();
        LOOP:
        do {
            System.out.println("======================================================");
            System.out.println("===================      ADMIN     ===================");
            System.out.println("======================================================");
            System.out.println("                           | Jumlah User : " + jumlahuser);
            System.out.println("------------------------------------------------------");
            System.out.println("Opsi Pilihan : ");
            System.out.println("1. Lihat Data Riwayat Transaksi");
            System.out.println("2. Hapus Data Riwayat Transaksi");
            System.out.println("3. Lihat Aktivitas User");
            System.out.println("4. Log out");
            System.out.println("5. Exit");
            System.out.println("------------------------------------------------------");
            System.out.print  ("Masukkan Pilihan Anda : ");
            String inputan = input.next();
            if (inputan.equals("1")) {
                boolean isLanjut = lihatDataTransaksi();
                if (isLanjut) {
                    continue LOOP;
                } else {
                    lanjut = false;
                    break LOOP;
                }
            }
            else if (inputan.equals("2")) {
                hapusdatariwayatTransaksi();
                continue LOOP;
            }
            else if (inputan.equals("3")) {
                boolean islanjut = readAktivitasUser();
                if (islanjut) {
                    continue LOOP;
                } else {
                    lanjut = false;
                    break LOOP;
                }
            }
            else if (inputan.equals("4")) {
                lanjut = true;
                break LOOP;
            }
            else if (inputan.equals("5")) {
                lanjut = false;
                break LOOP;
            }
            else {
                clrscr();
                System.out.println("Warning : inputan Tidak Valid !");
            }
        } while (true);

        return lanjut;
    }

    private static boolean lihatDataTransaksi() throws IOException {
        Scanner input = new Scanner(System.in);
        boolean isLanjut;

        try {
            FileReader filedatabase = new FileReader("RiwayatTransaksi.txt");
            filedatabase.close();
        } catch (Exception e) {
            FileWriter createfileRiwayat = new FileWriter("RiwayatTransaksi.txt");
            createfileRiwayat.close();
        }

        FileReader filedatabase = new FileReader("RiwayatTransaksi.txt");
        BufferedReader readFileriwayat = new BufferedReader(filedatabase);
        String dataRiwayat = readFileriwayat.readLine();

        if (dataRiwayat == null) {
            clrscr();
            System.out.println("Belum Ada Transaksi Yang Dilakukan !");
            isLanjut = true;
        } else  {
            clrscr();
            System.out.println("=====================================================================");
            System.out.println("             RIWAYAT TRANSAKSI YANG DILAKUKAN OLEH USER              ");
            System.out.println("=====================================================================");
            while (dataRiwayat != null) {
                System.out.println(dataRiwayat);
                dataRiwayat = readFileriwayat.readLine();
            }

            System.out.println("\n1. Back\n2. Exit");
            do {
                System.out.print("Masukkan Pilihan Anda : ");
                String inputan2 = input.next();
                if (inputan2.equals("1")) {
                    clrscr();
                    isLanjut = true;
                    break;
                } else if (inputan2.equals("2")) {
                    isLanjut = false;
                    break;
                } else {
                    System.out.println("Warning : inputan Tidak Valid !");
                }
            } while (true);

        }

        filedatabase.close();
        readFileriwayat.close();

        return isLanjut;
    }

    private static void hapusdatariwayatTransaksi() throws IOException {

        try {
            FileReader filedatabase = new FileReader("RiwayatTransaksi.txt");
            filedatabase.close();
        } catch (Exception e) {
            FileWriter createfileRiwayat = new FileWriter("RiwayatTransaksi.txt");
            createfileRiwayat.close();
        }

        FileReader filedatabase = new FileReader("RiwayatTransaksi.txt");
        BufferedReader readFileriwayat = new BufferedReader(filedatabase);
        String dataRiwayat = readFileriwayat.readLine();

        if (dataRiwayat == null) {
            filedatabase.close();
            readFileriwayat.close();
            clrscr();
            System.out.println("Data Riwayat Transaksi Sudah Kosong !");
        } else  {
            boolean isDelete = shopping.getYesorNo("Ingin Menghapus Data Riwayat Transaksi ? (ya/no) : ");
            if (isDelete) {
                filedatabase.close();
                readFileriwayat.close();
                File file = new File("RiwayatTransaksi.txt");
                file.delete();
                clrscr();
                System.out.println("Data Riwayat Transaksi Berhasil Dihapus");
            } else {
                clrscr();
                filedatabase.close();
                readFileriwayat.close();
            }

        }

    }

    private static boolean readAktivitasUser() {
        Scanner input = new Scanner(System.in);
        boolean lanjut = true;
        try {
            File fileaktivitas = new File("AktivitasUser.txt");
            FileReader file = new FileReader(fileaktivitas);
            BufferedReader bufferinput = new BufferedReader(file);
            String data = bufferinput.readLine();
            clrscr();
            System.out.println("===============================================================");
            System.out.println("                         AKTIVITAS USER                        ");
            System.out.println("===============================================================");
            while (data != null) {
                System.out.println(data);
                data = bufferinput.readLine();
            }
            System.out.println("\n1. Hapus Data Aktivitas User");
            System.out.println("2. Back");
            System.out.println("3. Exit");
            do {
                System.out.print  ("Masukkan Pilihan Anda : ");
                String inputan = input.next();
                if (inputan.equals("1")) {
                    boolean isDelete = shopping.getYesorNo("Ingin Menghapus Data Aktivitas ? (ya/no) : ");
                    if (isDelete) {
                        file.close();
                        bufferinput.close();
                        if (fileaktivitas.delete()) {
                            clrscr();
                            System.out.println("File Data Aktivitas Berhasil Dihapus");
                            lanjut = true;
                            break;
                        } else {
                            clrscr();
                            System.out.println("Error");
                            lanjut = true;
                            break;
                        }
                    } else {
                        clrscr();
                        lanjut = true;
                        break;
                    }
                } else if (inputan.equals("2")) {
                    clrscr();
                    lanjut = true;
                    break;
                } else if (inputan.equals("3")) {
                    clrscr();
                    lanjut = false;
                    break;
                } else {
                    System.out.println("Warning : Inputan Tidak Valid !");
                }
            } while (true);

            file.close();
            bufferinput.close();

        } catch (Exception e) {
            clrscr();
            System.out.println("Aktivitas User Belum ada !");
            lanjut = true;
        }

        return lanjut;
    }

    private static int jumlahUser() {
        int jumlahuser = 0;

        try {
            FileReader file = new FileReader("Email&Password_User.txt");
            BufferedReader fileread = new BufferedReader(file);
            String data = fileread.readLine();
            while (data != null) {
                jumlahuser++;
                data = fileread.readLine();
            }

            file.close();
            fileread.close();
        } catch (Exception e) {

        }

        return jumlahuser;
    }

    private static void GetUserEmail(String email) throws IOException {

        FileWriter fileoutput = new FileWriter("EmailUser.txt");
        BufferedWriter writeEmail = new BufferedWriter(fileoutput);

        writeEmail.write(email);
        writeEmail.newLine();
        writeEmail.flush();

        fileoutput.close();
        writeEmail.close();

    }

    private static void writeAktivitasUserBaru(String aktivitas) throws IOException {

        FileWriter filewrite = new FileWriter("AktivitasUser.txt", true);
        BufferedWriter writeAktivitas = new BufferedWriter(filewrite);

        writeAktivitas.write(aktivitas);
        writeAktivitas.newLine();
        writeAktivitas.write("-----------------------------------------------------------");
        writeAktivitas.newLine();

        writeAktivitas.flush();

        filewrite.close();
        writeAktivitas.close();
    }

    /* DATA USER */
    private static String createDatauser() {

        Scanner input = new Scanner(System.in);
        String nama = "", provinsi = "", kota = "", sesuai;
        String datauser = "";
        long nomorhp;
        boolean adaSpasi = false;

        LOOP:
        do {
            clrscr();
            System.out.println("======================================================");
            System.out.println("=====================   Sign Up   ====================");
            System.out.println("======================================================");
            System.out.println("         Silahkan Mengisi Data Dengan Sesuai !        ");
            System.out.println("------------------------------------------------------");

            //nama
            nama = getnama("Nama Lengkap : ");

            //nomor hp
            nomorhp = getnomorhp("Nomor Hp     : +628 ");

            System.out.println();
            System.out.println("======================================================");
            System.out.println("         Silahkan Mengisi Alamat Dengan Sesuai        ");
            System.out.println("------------------------------------------------------");

            //provinsi
            provinsi = getProvinsi("Provinsi     : ");

            //kota
            kota = getKota( "Kota         : ");

            clrscr();
            do {
                System.out.println("======================================================");
                System.out.println("=====================   Sign Up   ====================");
                System.out.println("======================================================");
                System.out.println("Data Anda   : ");
                System.out.println("\tNama     : " + nama);
                System.out.println("\tNomor Hp : +628 " + nomorhp);
                System.out.println("Alamat Anda : ");
                System.out.println("\tProvinsi : " + provinsi);
                System.out.println("\tKota     : " + kota);

                System.out.print  ("\nApakah Data Sudah Sesuai? (ya/no) : ");
                sesuai = input.nextLine();
                if (sesuai.equals("ya")) {
                    clrscr();
                    System.out.println("Sign Up Success,Silahkan Login");
                    datauser = String.format("%s,+628%s,%s,%s", nama,nomorhp,kota,provinsi);
                    break LOOP;
                } else if (sesuai.equals("no")) {
                    continue LOOP;
                } else {
                    clrscr();
                    System.out.println("Warning : Masukkan Inputan yang Valid !");
                }
            } while(!sesuai.equals("ya") || !sesuai.equals("no"));

        } while(sesuai.equals("no"));


        return datauser;
    }

    public static long getnomorhp(String message) {
        Scanner input = new Scanner(System.in);
        long nomorhp;

        try {
            System.out.print(message);
            nomorhp = input.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Warning : Masukkan Nomor HP yang Valid");
            nomorhp = getnomorhp(message);
        }

        return nomorhp;
    }

    public static String getnama(String message) {
        String nama = "";
        Scanner input = new Scanner(System.in);
        boolean adaSpasi = false;

        do {
            try {
                System.out.print  (message);
                nama = input.nextLine();
                adaSpasi = nama.substring(0,1).equals(" "); //cek inputan nama yang dimasukkan user jika ada spasi di index pertama
                if (adaSpasi) {
                    System.out.println("Warning : Masukkan Nama Anda Dengan Benar !");
                } else if (nama.contains("/") || nama.contains(",") || nama.contains("_")) {
                    System.out.println("Warning : Masukkan Nama Anda Dengan Benar !");
                    adaSpasi = true;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Warning : Masukkan Nama Anda !");
            }
        } while (nama.equals("") || adaSpasi);

        return  nama;
    }

    public static String getProvinsi(String meesage) {
        String provinsi = "";
        boolean adaSpasi = false;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.print  (meesage);
                provinsi = input.nextLine();
                adaSpasi = provinsi.substring(0,1).equals(" ");
                if (adaSpasi) {
                    System.out.println("Warning : Masukkan Provinsi Anda Dengan Benar");
                } else if (provinsi.contains(",") || provinsi.contains("/") || provinsi.contains("_")) {
                    System.out.println("Warning : Masukkan Provinsi Anda Dengan Benar");
                    adaSpasi = true;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Warning : Masukkan Provinsi Anda !");
            }

        } while (provinsi.equals("") || adaSpasi);

        return provinsi;
    }

    public static String getKota(String message) {
        String kota = "";
        boolean adaSpasi = false;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.print  (message);
                kota = input.nextLine();
                adaSpasi = kota.substring(0,1).equals(" ");
                if (adaSpasi) {
                    System.out.println("Warning : Masukkan Kota Anda Dengan Benar!");
                } else if (kota.contains(",") || kota.contains("/") || kota.contains("_")) {
                    System.out.println("Warning : Masukkan Kota Anda Dengan Benar!");
                    adaSpasi = true;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Warning : Masukkan Kota Anda !");
            }

        } while (kota.equals("") || adaSpasi);

        return kota;
    }

    /* PROFIL */
    private static void profil() {
        clrscr();
        System.out.println("======================================================");
        System.out.println("===================     PROFIL     ===================");
        System.out.println("======================================================");
        System.out.println("\tMade By : Ahmad Fathanah M.Adil       ");
        System.out.println("\tBirth   : Maros, 15 November 2001     ");
        System.out.println("\tAddres  : Kendari, Southeast Sulawesi,");
        System.out.println("\t          Indonesia                   ");
        System.out.println("\tStudy   : A Student at Hasanuddin     ");
        System.out.println("\t          University, Makassar        ");
        System.out.println("\t          Majoring in                 ");
        System.out.println("\t          Informatic Engineering      ");
        System.out.println("------------------------------------------------------");
        System.out.println("                       Made in :                      ");
        System.out.println("                Kendari, 18 Juli 2020                 ");
        System.out.println("======================================================");
    }

    public static void canceled()  {
        clrscr();
        System.out.println("======================================================");
        System.out.println("===================   PT.SHOPAPP   ===================");
        System.out.println("======================================================");
        System.out.println("\tTRANSACTION CANCELED !");
        System.out.println("\tTerima Kasih :)");
        System.out.println();
        System.out.println("======================================================");

        File file1 = new File("Keranjang_User.txt");
        file1.delete();

    }

    public static void clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

}