import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLyCanBo {
    private List<CanBo> canBoList = new ArrayList<>();

    public void themMoiCanBo(CanBo canBo) {
        this.canBoList.add(canBo);
    }

    public CanBo timKiemCanBoTheoHoTen(String name) {
        for (int i = 0; i < canBoList.size(); i++) {
            if (canBoList.get(i).getName() == name) {
                CanBo canBo = canBoList.get(i);
                return canBo;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        QuanLyCanBo quanLyCanBo = new QuanLyCanBo();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Chon hanh dong ban muon thuc hien:\n" + "Nhap 1 - neu ban muon them thong tin can bo\n" + "Nhap 2 - neu ban muon tim kiem can bo theo ten");
            int action = scanner.nextInt();
            if (action == 1) {
                System.out.println("Ban can cho chung toi biet ban la ai:\n" + "Nhap 1 - neu ban la cong nhan\n" + "Nhap 2 - neu ban la ky su\n" + "Nhap 3 - neu ban la nhan vien\n");
                System.out.print("Moi nhap: ");
                int canBoType = scanner.nextInt();
                switch (canBoType) {
                    case 1:
                        System.out.println("Moi nhap thong tin cua cong nhan:");
                        scanner.nextLine();
                        System.out.print("Ten: ");
                        String name1 = scanner.nextLine();
                        System.out.print("Tuoi: ");
                        int age1 = scanner.nextInt();
                        System.out.print("Gioi tinh: ");
                        int gender1 = scanner.nextInt();
                        System.out.print("Cap bac: ");
                        int level = scanner.nextInt();
                        CongNhan congNhan = new CongNhan(name1, age1, gender1, level);
                        quanLyCanBo.themMoiCanBo(congNhan);
                        break;
                    case 2:
                        System.out.println("Moi nhap thong tin cua ky su:");
                        scanner.nextLine();
                        System.out.print("Ten: ");
                        String name2 = scanner.nextLine();
                        System.out.print("Tuoi: ");
                        int age2 = scanner.nextInt();
                        System.out.print("Gioi tinh: ");
                        int gender2 = scanner.nextInt();
                        System.out.print("Chuyen nganh: ");
                        String specialized = scanner.nextLine();
                        KySu kySu = new KySu(name2, age2, gender2, specialized);
                        quanLyCanBo.themMoiCanBo(kySu);
                        break;
                    case 3:
                        System.out.println("Moi nhap thong tin cua nhan vien:");
                        scanner.nextLine();
                        System.out.print("Ten: ");
                        String name3 = scanner.nextLine();
                        System.out.print("Tuoi: ");
                        int age3 = scanner.nextInt();
                        System.out.print("Gioi tinh: ");
                        int gender3 = scanner.nextInt();
                        System.out.print("Chuyen nganh: ");
                        String job = scanner.nextLine();
                        NhanVien nhanVien = new NhanVien(name3, age3, gender3, job);
                        quanLyCanBo.themMoiCanBo(nhanVien);
                        break;
                }
            } else if (action == 2) {
                System.out.print("Moi ban nhap ten can bo muon tim kiem: ");
                String tenCanBo = scanner.nextLine();
                for (int j = 0; j < quanLyCanBo.canBoList.size(); j++) {
                    if (quanLyCanBo.canBoList.get(j).getName() == tenCanBo) {
                        System.out.println("Thong tin can bo:");
                        System.out.println("Ten: " + quanLyCanBo.canBoList.get(j).getName());
                        System.out.println("Tuoi: " + quanLyCanBo.canBoList.get(j).getAge());
                        System.out.println("Gioi tinh: " + quanLyCanBo.canBoList.get(j).getGender());
                    }
                }
            } else {
                System.out.println("Xin moi nhap lai, ko co lua chon nay!");
                System.out.println("Chon hanh dong ban muon thuc hien:\n" + "Nhap 1 - neu ban muon them thong tin can bo\n" + "Nhap 2 - neu ban muon tim kiem can bo theo ten");
                action = scanner.nextInt();
            }
        }
    }
}