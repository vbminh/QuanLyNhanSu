package application.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import application.Models.NhanSu;

public class ListNhanSu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<NhanSu> list = new ArrayList<NhanSu>();
	
	
	public boolean ThemNS(NhanSu ns) {
		try {
            if (list.contains(ns)) {
                return false;
            } else {
                list.add(ns);
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
	}
	
	public void Sua(String mans, NhanSu newns) {
		NhanSu ns = new NhanSu(mans);
		list.set(list.indexOf(ns), newns);
	}
	
	public void xoa(NhanSu ns) {
		int index = list.indexOf(ns);
        list.remove(index);
    }
	
	public NhanSu getNhanSu(int index) {
		return list.get(index);
	}
	
	public int getSize() {
		return list.size();
	}
	
	public void them() {
		list.add(new NhanSu("NV0001", "Dang Van A", "nam", "01/01/1969", "0987654321", "DVA@gmail.com", "Ha Noi", "Ban Giam Hieu", "Hieu truong", "Hieu truong", "21/12/2010", ""));
		list.add(new NhanSu("NV0002", "Ha Thi C", "nu", "21/02/1973", "0235454321", "HTC@gmail.com", "Hai Phong", "Ban Giam Hieu", "Pho hieu truong", "Pho hieu truong", "11/11/2013", ""));
		list.add(new NhanSu("NV0003", "Dao Thu G", "nu", "14/06/1972", "0865754321", "DTG@gmail.com", "Bac Ninh", "Phong To chuc Hanh chinh", "Nhan vien", "Truong phong", "12/05/2012", ""));
		list.add(new NhanSu("NV0004", "Hoang Mai V", "nam", "04/07/1988", "08534654321", "HMV@gmail.com", "Ha Noi", "Phong Ke toan Kiem Toan", "Nhan vien", "Truong phong", "23/02/2017", ""));
		list.add(new NhanSu("NV0005", "Ngo Duc N", "nam", "21/03/1979", "0342654686", "NDN@gmail.com", "Ha Noi", "Khoa Cong nghe thong tin", "Giang vien", "", "14/08/2018", ""));
		list.add(new NhanSu("NV0006", "Tran Thao M", "nu", "13/09/1969", "0453654857", "TTM@gmail.com", "Hai Duong", "Khoa Co khi", "Giang vien", "Pho khoa", "09/12/2010", ""));
		list.add(new NhanSu("NV0007", "Pham Thi Z", "nu", "10/04/1976", "0146654986", "PTZ@gmail.com", "Phu Tho", "Khoa Du lich", "Giang vien", "Truong khoa", "21/03/2013", ""));
		list.add(new NhanSu("NV0008", "Hoang Thi T", "nu", "02/10/1974", "0242654467", "HTT@gmail.com", "Phu Tho", "Phong Cong tac sinh vien", "Nhan vien", "", "19/05/2018", ""));
		list.add(new NhanSu("NV0009", "Doan Linh B", "nu", "14/11/1970", "0645654865", "DLB@gmail.com", "Ha Noi", "Phong Quan tri", "Nhan vien", "Pho phong", "25/10/2021", ""));
		list.add(new NhanSu("NV0010", "Cao Quoc Q", "nam", "05/12/1969", "0888654886", "CQQ@gmail.com", "Hai Duong", "Khoa Dien", "Hieu truong", "Truong khoa", "11/01/2011", ""));
		list.add(new NhanSu("NV0011", "Le Hao H", "nam", "12/01/1973", "0666654332", "LHH@gmail.com", "Bac Ninh", "Bao ve", "Nhan vien", "", "23/11/2016", ""));
		list.add(new NhanSu("NV0012", "Bui Nguyen K", "nam", "06/06/1971", "0234654342", "BNK@gmail.com", "Hai Phong", "Bao ve", "Nhan vien", "", "14/02/2010", ""));
		list.add(new NhanSu("NV0013", "Vo Duc S", "nam", "05/12/1985", "0743654123", "VDS@gmail.com", "Ha Noi", "Ve sinh", "Nhan vien", "", "21/07/2014", ""));
		list.add(new NhanSu("NV0014", "Le Thi T", "nu", "21/11/1970", "0235654235", "LTT@gmail.com", "Ha Noi", "Ve sinh", "Nhan vien", "", "12/12/2018", ""));
	}
}
