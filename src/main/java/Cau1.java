import java.io.*;
import java.util.*;

public class Cau1 {
    public static void main(String[] args) throws IOException {
        //1. Đọc danh sách sinh viên từ tập tin data.csv được cung cấp
        List<SinhVien> dsSinhVien = readData("C:\\Users\\trungnpm\\IdeaProjects\\exam\\src\\main\\data.csv");
        System.out.println(dsSinhVien.toString());
        System.out.println("------------------------------");

        //2. Liệt kê danh sách 10 sinh viên có điểm thi lý thuyết cao nhất
        System.out.println("Danh sách 10 sinh viên có điểm thi lý thuyết cao nhất: ");
        getTop10SVdiemLT(dsSinhVien);
        for (int i = 0; i < 10; i++) {
            System.out.println(dsSinhVien.get(i));
        }
        System.out.println("------------------------------");

//        3. Tính điểm tổng kết cho từng sinh viên theo công thức: bonus 10%,
//        report 30%, app 15%, lý thuyết 45%; điểm tổng kết được làm tròn đến
//        0.5 (ví dụ: 7.37 -> 7.5, 6.2 -> 6.0)
        tinhTB(dsSinhVien);
        System.out.println("------------------------------");

//        4. Liệt kê danh sách 10 sinh viên có điểm tổng kết thấp nhất lên giao diện
//        console.
        System.out.println("Danh sách 10 sinh viên có điểm tổng kết thấp nhất: ");
        dsTBthapnhat(dsSinhVien);
        System.out.println("------------------------------");

//        5. Xuất danh sách sinh viên gồm đầy đủ thông tin, bao gồm cả điểm tổng
//        kết vào tập tin output.csv, cột tổng kết được chèn vào phía cuối
        File csvOutputFile = new File("output.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            for (int i = 0; i < dsSinhVien.size(); i++) {
                pw.println(dsSinhVien.get(i).toString());
            }
        }
        System.out.println("------------------------------");

//        6. Hiển thị bảng thống kê tình hình lớp, gồm các thông tin như trong
//        bảng bên dưới

    }

    public static List<SinhVien> readData(String filePath) throws IOException {
        List<SinhVien> result = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("ID,Name,Email,Bonus,Report,App,LT"))
                continue;
            else {
                List<String> data = Arrays.asList(line.split(","));
                SinhVien sv = new SinhVien();
                sv.setID(Integer.parseInt(data.get(0)));
                sv.setName(data.get(1));
                sv.setEmail(data.get(2));
                sv.setBonus(Double.parseDouble(data.get(3)));
                sv.setReport(Double.parseDouble(data.get(4)));
                sv.setApp(Double.parseDouble(data.get(5)));
                sv.setLt(Double.parseDouble(data.get(6)));
                result.add(sv);
            }
        }
        return result;
    }

    public static List<SinhVien> getTop10SVdiemLT(List<SinhVien> dsSinhVien) {
        List<SinhVien> getTop10SVdiemLT = new ArrayList<>();
        int n = dsSinhVien.size();
        SinhVien temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (dsSinhVien.get(j - 1).getLt() < dsSinhVien.get(j).getLt()) {
                    temp = dsSinhVien.get(j - 1);
                    dsSinhVien.set(j - 1, dsSinhVien.get(j));
                    dsSinhVien.set(j, temp);
                }
            }
        }
        for (int i = 1; i <= 10; i++) {
            getTop10SVdiemLT.add(dsSinhVien.get(i));
        }
        return getTop10SVdiemLT;
    }

    public static void tinhTB(List<SinhVien> sinhvien) {
        for (int i = 0; i < sinhvien.size(); i++) {
            sinhvien.get(i).setTB(Math.round((sinhvien.get(i).getBonus() * 0.1 + sinhvien.get(i).getReport() * 0.3 + sinhvien.get(i).getApp() * 0.15 + sinhvien.get(i).getLt() * 0.45) * 10) / 10.0);
            sinhvien.get(i).setTB(Math.round(sinhvien.get(i).getTB() * 2) / 2.0);
        }
    }

    public static void dsTBthapnhat(List<SinhVien> sinhvien) {
        Collections.sort(sinhvien, new Comparator<SinhVien>() {
            public int compare(SinhVien o1, SinhVien o2) {
                return Double.compare(o1.getTB(), o2.getTB());
            }
        });
        for (int i = 0; i < 10; i++) {
            System.out.println(sinhvien.get(i).getName() + " " + "Điểm TB: " + sinhvien.get(i).getTB());
        }
    }
}
