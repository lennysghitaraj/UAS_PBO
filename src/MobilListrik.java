import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


// Merupakan inheritance dari Mobil
public class MobilListrik extends Transportasi{
    
    
    static Scanner input = new Scanner(System.in);
    
    // constructors
    public MobilListrik() {
    }

    public MobilListrik(String kodeTransport, String namaTransport, String PlatTransportasi, int JumlahPenumpang, String StatusTransport, int HargaSewa) {
        this.kodeTransport = kodeTransport;
        this.namaTransport = namaTransport;
        this.PlatTransportasi = PlatTransportasi;
        this.JumlahPenumpang = JumlahPenumpang;
        this.StatusTransport = StatusTransport;
        this.HargaSewa = HargaSewa;
    }

    public String getNamaMobilListrik() {
        return this.namaTransport;
    }

    public String getKodeMobilListrik() {
        return this.kodeTransport;
    }

    public void setKodeMobilListrik(String kodeMobilListrik) {
        this.kodeTransport = kodeMobilListrik;
    }

    public void setNamaMobilListrik(String namaMobilListrik) {
        this.namaTransport = namaMobilListrik;
    }

    public MobilListrik(String kodeMobilListrik, String namaMobilListrik,String PlatTransportasi,String StatusMobilListrik, int HargaSewa) {
        this.kodeTransport = kodeMobilListrik;
        this.namaTransport = namaMobilListrik;
        this.PlatTransportasi = PlatTransportasi;
        this.StatusTransport = StatusMobilListrik;
        this.HargaSewa = HargaSewa;
    }

    /* Nama                 : Lennys Ghitaraj
     * NIM                  : 03081210035
     * Deskripsi Singkat    : berfungsi untuk membalikkan arraylist dari text file
     */

    public static ArrayList<MobilListrik> updateMobilListrik (ArrayList<MobilListrik> listriks) throws FileNotFoundException, IOException {
        try (BufferedReader read = new BufferedReader(new FileReader("data/mobilListrik.txt"))) {
            String s = "";
            while ((s = read.readLine()) != null) {
                String data[] = s.split(",");
                listriks.add(new MobilListrik(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5])));
            }
        }
        return listriks;
    }

    /* Nama                 : Lennys Ghitaraj
     * NIM                  : 03081210035
     * Deskripsi Singkat    : (updateMobilListrik) berfungsi untuk mengupdate textfile dan arraylist jika ada 1 data yang perlu diganti
     *                        OverLoading dengan method diatas karena mempunyai nama yang sama dengan parameter berbeda
     */

    public static void updateMobilListrik (String kodeMobilListrik, String status) throws IOException{
        String FilePath = "data/mobilListrik.txt";
        File oldFile = new File ("data/mobilListrik.txt");
        File newFile = new File ("data/temp.txt");
        // L01,Tesla,BK 4455 NZ,5,Tersedia,500000
        try (BufferedReader br = new BufferedReader(new FileReader("data/mobilListrik.txt"))) {
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String s = "";
            int i = 0;
            while ((s = br.readLine()) != null) {
                String data[] = s.split(",");
                if (i == 0) {
                    if (data[0].equalsIgnoreCase(kodeMobilListrik)) {
                        String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + ",";
                        row = row + status + "," + data[5];
                        pw.print(row);
                    } else {
                        String row =  data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5];
                        pw.print(row);
                    }
                } else {
                    if (data[0].equalsIgnoreCase(kodeMobilListrik)) {
                        String row = "\n" +  data[0] + "," + data[1] + "," + data[2] + "," + data[3] + ",";
                        row = row + status + "," + data[4];
                        pw.print(row);
                    } else {
                        String row =  "\n" + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5];
                        pw.print(row);
                    }
                }
                i++;
            }
            br.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(FilePath);
            newFile.renameTo(dump);
        }
    }

    /* Nama                 : Lennys Ghitaraj
     * NIM                  : 03081210035
     * Deskripsi Singkat    : (displayAturanMobilListrik) berfungsi untuk menampilkan info mengenai mobil listrik hanya dengan kondisi tertentu
     */

    public static void displayAturanMobilListrik (String equals) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("data/mobilListrik.txt"))) {
            String s = "";
            System.out.println("|Kode\t|Jenis\t|Jlh Penumpang\t|Harga\t\t|");
            while ((s = read.readLine()) != null) {
                // System.out.println(s);
                String data[] = s.split(",");
                                
                if (data[4].equalsIgnoreCase(equals)) {
                    for (int i = 0; i < 6; i++) {
                        if ((i == 1)) {
                            System.out.print(data[i] + "\t|"); 
                        } else if (i == 0){
                            System.out.print("|" +data[i] + "\t|"); 
                        } else if ((i==5) || (i==3) ){
                            System.out.print(data[i] + "\t\t|"); 
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    /* Nama                 : Lennys Ghitaraj
     * NIM                  : 03081210035
     * Deskripsi Singkat    : (DaftarMobilListrik) berfungsi untuk mendaftar sebuah mobil listrik, lalu diinput ke textfile dan arraylist
     */

    public static void DaftarMobilListrik (ArrayList<MobilListrik> MobilListrik) throws Exception {
        System.out.println("Daftar Mobil Listrik Baru");
                System.out.println("-----------------");
                System.out.print("Nama Mobil Listrik : ");
                String namaMobil = input.next();
                System.out.print("Masukkan harga sewa : ");
                int harga = input.nextInt();
                System.out.print("Masukkan plat transportasi : ");
                String plat1 = input.next();
                String plat2 = input.next();
                String plat3 = input.next();
                String plat = plat1 + " " + plat2 + " " + plat3;
                System.out.print("Masukkan jumlah penumpang : ");
                int JumlahPenumpang = input.nextInt();
                String kodeListrik = "L0" + Integer.toString(MobilListrik.size()+1);

                //masukkan data pelanggan ke ArrayList
                MobilListrik.add(new MobilListrik(kodeListrik, namaMobil, plat,JumlahPenumpang, "Tersedia", harga));
                //masukkan data pelanggan ke file
                try (FileWriter pwMobil = new FileWriter("data/mobilListrik.txt", true)) {
                    pwMobil.append("\n" + kodeListrik + "," + namaMobil + ","  + plat + "," + JumlahPenumpang + ","  +  "Tersedia" + "," + harga);
                }
    }

    /* Nama                 : Lennys Ghitaraj
     * NIM                  : 03081210035
     * Deskripsi Singkat    : (SewaMobilListrik) berfungsi untuk menyewa sebuah mobil listrik, lalu diupdate status mobil listrik serta menambah transaksi pinjam dan pelanggan
     */

    public static void SewaMobilListrik (ArrayList<MobilListrik> listriks, ArrayList<TransaksiPeminjaman> pinjams, ArrayList<Pelanggan> pelanggans) throws Exception {
        
        String tanggalPinjam = util.inputTanggal("peminjaman");
        int durasi = util.inputDurasi("peminjaman");
        String lokasiPinjam = util.inputLokasi("peminjaman");
        util.clearScreen();

        //print all cars dgn status tersedia
        displayAturanMobilListrik( "Tersedia");
        //input kode mobil yang disewa
        System.out.print("Masukkan kode Mobil Listrik yang ingin disewakan : ");
        String kodeInput = input.next();
        kodeInput = kodeInput.toUpperCase();
        // util.clearScreen();
        //cetak detail mobil yang kodenya sama dengan inputan
        for (MobilListrik MobilListrik : listriks) {
            if (MobilListrik.getKodeMobilListrik().equalsIgnoreCase(kodeInput) && MobilListrik.getStatusTransport().equalsIgnoreCase("Tersedia")) {
                System.out.println("Kode MobilListrik : " + MobilListrik.getKodeMobilListrik());
                System.out.println("Nama MobilListrik : " + MobilListrik.getNamaMobilListrik());
                System.out.println("Plat MobilListrik : " + MobilListrik.getPlatTransportasi());
                System.out.println("Harga Sewa per Hari : Rp" + MobilListrik.getHargaSewa());
                int deposit = MobilListrik.getHargaSewa()/10;
                System.out.println("Harga Deposito : Rp" + deposit);
                System.out.println("-----------------------------------");
                int hargaTotal =  MobilListrik.getHargaSewa()*durasi + deposit;
                System.out.println("Harga total : Rp" + hargaTotal);
                System.out.print("Apakah anda yakin?(y/n) : ");
                String pilyakin = input.next();

                //jika yakin input data pelanggan
                if (pilyakin.equalsIgnoreCase("y")) {
                    util.clearScreen();
                    System.out.println("Masukkan detail pelanggan");
                    System.out.println("-------------------------");
                    System.out.print("Nama Pelanggan : ");
                    String namaPelanggan = input.next();
                    System.out.print("Nomor Telepon : ");
                    String noTelp = input.next();
                    System.out.print("Umur : ");
                    int umurPelanggan = input.nextInt();
                    if (umurPelanggan < 18) {
                        throw new Exception("Umur pelanggan tidak mencukupi");
                    }
                    System.out.print("Email : ");
                    String emailPelanggan = input.next();

                    String kodePelanggan = "P0" + Integer.toString(pelanggans.size()+1);
                    String kodePinjam = "T0" + Integer.toString(pinjams.size()+1);

                    //masukkan data pelanggan ke ArrayList
                    pelanggans.add(new Pelanggan(kodePelanggan, namaPelanggan, noTelp, umurPelanggan, emailPelanggan, "meminjam"));
                    //masukkan data pelanggan ke file
                    try (FileWriter pwPelanggan = new FileWriter("data/pelanggan.txt", true)) {
                        pwPelanggan.append("\n" + kodePelanggan + "," + namaPelanggan + "," + noTelp + "," + umurPelanggan + "," + emailPelanggan + "," + "meminjam");
                    }

                    //masukkan data pinjam ke ArrayList
                    pinjams.add(new TransaksiPeminjaman(kodePinjam, kodeInput, kodePelanggan, lokasiPinjam, tanggalPinjam, deposit, hargaTotal, durasi, "Meminjam"));
                    //masukkan data pinjam ke file
                    try (FileWriter pwPinjam = new FileWriter("data/peminjaman.txt", true)) {
                        pwPinjam.append( "\n" +kodePinjam + "," + kodeInput + "," + kodePelanggan + "," + lokasiPinjam + "," + tanggalPinjam + "," + deposit + "," + hargaTotal + "," + durasi + ",Meminjam");
                    }
                    Mobil.updateMobil(kodeInput, "Dipinjam");

                    //cetak reciept
                    util.clearScreen();
                    TransaksiPeminjaman.cetakRecieptPinjam(kodePinjam,pinjams);
                }
                break;
            }
        }
    }

    /* Nama                 : Lennys Ghitaraj
     * NIM                  : 03081210035
     * Deskripsi Singkat    : (kembaliMobilListrik) berfungsi untuk mengembalikan sebuah mobil listrik, lalu diupdate status mobil, transaksi pinjam dan pelanggan serta menambah transaksi kembali
     */

    public static void kembaliMobilListrik (ArrayList<MobilListrik> MobilListrik, ArrayList<TransaksiPeminjaman> pinjams, ArrayList<Pelanggan> pelanggans, ArrayList<TransaksiPengembalian> kembalis) throws FileNotFoundException, IOException, ParseException{
        TransaksiPeminjaman.displayAturanPinjam ("Meminjam", "l");
        //masukkan nomor peminjaman yang mau dikembalikan
        System.out.print("Masukkan kode transaksi : ");
        String kodeInput = input.next();
        kodeInput = kodeInput.toUpperCase();
        util.clearScreen();
        for (TransaksiPeminjaman pinjam : pinjams) {
            if (pinjam.getNomorTransaksi().equalsIgnoreCase(kodeInput) && pinjam.getStatusPinjam().equalsIgnoreCase("Meminjam")) {
                System.out.println("Nomor Transaksi : " + pinjam.getNomorTransaksi());
                System.out.println("Nama Mobil Listrik : " + pinjam.getListrikPinjam().getNamaTransport());
                System.out.println("Plat Mobil Listrik : " + pinjam.getListrikPinjam().getPlatTransportasi());
                System.out.println("Peminjam : " + pinjam.getPelangganPinjam().getNamaPelanggan());
                System.out.println("-----------------------");
                String tanggalKembali = util.inputTanggal("pengembalian");
                String lokasiKembali = util.inputLokasi("pengembalian");

                String tanggalKembaliSeharusnya = util.addToDate(pinjam.getTanggalPinjam(), pinjam.getLamaSewa());
                int dendaHari = 0;
                int bedaHari;
                if (tanggalKembali.equalsIgnoreCase(tanggalKembaliSeharusnya)) {
                    System.out.println("Mobil Listrik dikembalikan tepat waktu");
                    dendaHari += 0;
                } else {
                    if ((bedaHari = util.getDifferenceDays(tanggalKembali, tanggalKembaliSeharusnya)) > 0) {
                        System.out.printf("Mobil Listrik dikembalikan %d hari terlambat\n", bedaHari);
                        dendaHari = bedaHari * 50000;
                    } else {
                        System.out.println("Mobil Listrik dikembalikan lebih awal");
                        dendaHari += 0;
                    } 
                }
                //cek mobil from class mobil
                int dendaCek = Mobil.cekTransport() * 50000;
                //hitung denda total
                int totalDenda = dendaHari + dendaCek - pinjam.getDeposit();

                TransaksiPeminjaman peminjaman = TransaksiPeminjaman.cariTransaksiPinjam(kodeInput, pinjams);
                Pelanggan.updatePelanggan(peminjaman.getPelangganPinjam().getKodePelanggan(), "lunas");
                updateMobilListrik(peminjaman.getListrikPinjam().getKodeTransport(), "Tersedia");
                TransaksiPeminjaman.updatePinjam(kodeInput, "Berhasil");

                //masukkan data kembali ke ArrayList
                kembalis.add(new TransaksiPengembalian(kodeInput, lokasiKembali, tanggalKembali, totalDenda));
                //masukkan data kembali ke file
                try (FileWriter pwKembali = new FileWriter("data/pengembalian.txt", true)) {
                    pwKembali.append("\n" + kodeInput + "," + lokasiKembali + "," + tanggalKembali + "," + totalDenda);
                }
                util.clearScreen();
                TransaksiPengembalian.cetakRecieptKembali(kodeInput, kembalis, pinjams);
                break;
            }
        }
    }

    /* Nama                 : Lennys Ghitaraj
     * NIM                  : 03081210035
     * Deskripsi Singkat    : (displayAturanMobilListrikAsc) berfungsi untuk menampilkan mobil listrik berdasarkan harga tertinggi menggunakan selection sort
     *                        OverRide method dari class Transportasi
     */

    public static void displayAturanMobilListrikAsc (String equals) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("data/mobilListrik.txt"))) {
            String s = "";
            ArrayList <MobilListrik> asc = new ArrayList<>();
            while ((s = read.readLine()) != null) {
                
                String data[] = s.split(",");
                
                if (data[4].equalsIgnoreCase(equals)) {
                    asc.add (new MobilListrik(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5])));
                }
            }
            for (int i = 0; i < asc.size()-1; i++) {
                int min = i;
                for (int j = i+1; j < asc.size(); j++) {
                    if (asc.get(j).getHargaSewa() < asc.get(min).getHargaSewa()) {
                        MobilListrik temp = new MobilListrik();
                        temp = asc.get(min);
                        asc.set(min, asc.get(j));
                        asc.set(j, temp);
                    }
                }
            }
            System.out.println("|Kode\t|Jenis\t|Harga\t|");
            for (MobilListrik MobilListrik : asc) {
                System.out.println(MobilListrik);
            }
        }
    }

    /* Nama                 : Lennys Ghitaraj
     * NIM                  : 03081210035
     * Deskripsi Singkat    : (cekTransport) berfungsi untuk mengecek kondisi mobil listrik
     *                        OverRide method dari class Transportasi
     */

    public static int cekTransport (){
        int total = 0;
        int data[] = new int[4];
        System.out.println("Cek Transport");
        System.out.println("---------");
        System.out.println("0 jika terpenuhi, 1 jika tidak");
        System.out.print("Mesin jalan lancar : ");
        data[1] = input.nextInt()*3;
        System.out.print("Bodi tidak tergores : ");
        data[2] = input.nextInt()*3;
        System.out.print("Interior bersih : ");
        data[3] = input.nextInt();

        for (int i : data) {
            total += i;
        }
        return total;
    }

    @Override
    public String toString() {
        return "|" +  getKodeTransport() + "\t|"
        + getNamaTransport() + "\t|" 
        + getHargaSewa() + "\t|";
    }
}
