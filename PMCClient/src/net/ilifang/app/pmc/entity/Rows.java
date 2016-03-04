package net.ilifang.app.pmc.entity;
import java.util.List;
public class Rows {
	private int total;
    private List<RowInfo> rows;
    public Rows(int total, List<RowInfo> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return this.total;
    }
    public void setRows(List<RowInfo> rows) {
        this.rows = rows;
    }
    public List<RowInfo> getRows() {
        return this.rows;
    }
}
