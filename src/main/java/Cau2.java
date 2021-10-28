import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Cau2 extends Cau1 {
    public static void main(String[] args) throws IOException {
        List<SinhVien> dsSinhVien = readData("C:\\Users\\trungnpm\\IdeaProjects\\exam\\src\\main\\data.csv");

        //1. Cho biết chuỗi regular-expression để capture được các địa chỉ gmail và outlook
        Pattern pattern = Pattern.compile("^(.+)@(gmail|outlook)(.+)$");

        //2. Xây dựng hàm liệt kê thông tin các sinh viên có email là gmail
        Pattern patternGmail = Pattern.compile("^(.+)@gmail(.+)$");
        List<SinhVien> dsSinhVienGMail = getSV(dsSinhVien, patternGmail);
        System.out.println(dsSinhVienGMail);
        System.out.println("------------------------------");

        //3. Chuyển thông tin các sinh viên có email là outlook (II.2) vào tập
        //tin outlook.bin, sử dụng kỹ thuật Serialization
        Pattern patternOutlook = Pattern.compile("^(.+)@outlook(.+)$");
        List<SinhVien> dsSinhVienOutlook = getSV(dsSinhVien, patternOutlook);
        System.out.println(dsSinhVienOutlook);
        try {
            FileOutputStream fos = new FileOutputStream("outlook.bin");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DataOutputStream dos = new DataOutputStream(bos);
            for (SinhVien sv : dsSinhVienOutlook) {
                dos.writeInt(sv.getID());
                dos.writeUTF(sv.getName());
                dos.writeUTF(sv.getEmail());
                dos.writeDouble(sv.getBonus());
                dos.writeDouble(sv.getReport());
                dos.writeDouble(sv.getApp());
                dos.writeDouble(sv.getLt());
            }
            dos.flush();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------");

       //4. Xây dựng hàm đọc thông tin các sinh viên ở II.3 lên màn hình console
        try {
            FileInputStream fis = new FileInputStream("outlook.bin");
            DataInputStream dis = new DataInputStream(fis);
            while (dis.available() > 0) {
                int ID = dis.readInt();
                String name = dis.readUTF();
                String email = dis.readUTF();
                double bonus = dis.readDouble();
                double report = dis.readDouble();
                double app = dis.readDouble();
                double lt = dis.readDouble();
                SinhVien dsSVOutlook = new SinhVien(ID, name, email, bonus, report, app, lt);
                System.out.println(dsSVOutlook);
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------");
    }

    public static List<SinhVien> getSV(List<SinhVien> dsSinhVien, Pattern pattern) {
        List<SinhVien> results = new ArrayList<>();
        int n = dsSinhVien.size();
        for (SinhVien sinhVien : dsSinhVien) {
            Matcher matcher = pattern.matcher(sinhVien.email);
            if (matcher.find()) {
                results.add(sinhVien);
            }
        }
        return results;
    }
}
