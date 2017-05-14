package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.lcl.my12306.dao.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class Daily_report {

	protected Shell shlSw;
	private Display display;
	private Table table;
	private Table table_1;


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Daily_report window = new Daily_report();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shlSw.open();
		shlSw.layout();
		while (!shlSw.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSw = new Shell();
		shlSw.setImage(SWTResourceManager.getImage(Daily_report.class, "/image/12306_logo.jpg"));
		shlSw.setSize(450, 300);
		shlSw.setText("日志查询");
		shlSw.setBounds(0, 0, display.getClientArea().width,display.getClientArea(). height);
		shlSw.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shlSw, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);

		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);

		final CalendarCombo calendarCombo_2 = new CalendarCombo(composite_3, SWT.NONE);
		calendarCombo_2.setBounds(40, 10, 115, 25);

		final CalendarCombo calendarCombo_3 = new CalendarCombo(composite_3, SWT.NONE);
		calendarCombo_3.setBounds(40, 111, 115, 25);

		Label label_1 = new Label(composite_3, SWT.SEPARATOR);
		label_1.setBounds(102, 42, 2, 64);

		Label label_2 = new Label(composite_3, SWT.NONE);
		label_2.setText("操作时间段");
		label_2.setBounds(30, 61, 66, 17);

		Label label_3 = new Label(composite_3, SWT.NONE);
		label_3.setText("    查询");
		label_3.setBounds(30, 76, 61, 17);

		final Combo combo_1 = new Combo(composite_3, SWT.NONE);
		combo_1.setBounds(40, 218, 115, 25);

		Label label_4 = new Label(composite_3, SWT.NONE);
		label_4.setText("操作车站对象查询");
		label_4.setBounds(30, 195, 115, 17);

		Button button = new Button(composite_3, SWT.NONE);
		button.setText("查询");
		button.setBounds(52, 271, 100, 42);

		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(120);
		tblclmnNewColumn.setText("站点编号");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(120);
		tblclmnNewColumn_2.setText("站点名");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(120);
		tableColumn.setText("修改时间");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(120);
		tableColumn_1.setText("相关人员ID");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(130);
		tableColumn_2.setText("开始日期");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(130);
		tableColumn_5.setText("结束日期");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(120);
		tableColumn_3.setText("状态");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(279);
		tableColumn_4.setText("备注/详情");
		sashForm_1.setWeights(new int[] {205, 1142});

		Composite composite_2 = new Composite(sashForm, SWT.NONE);

		SashForm sashForm_2 = new SashForm(composite_2, SWT.NONE);
		sashForm_2.setBounds(0, 0, 1350, 343);

		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);

		final CalendarCombo calendarCombo = new CalendarCombo(composite_5, SWT.NONE);
		calendarCombo.setBounds(40, 10, 115, 25);

		final CalendarCombo calendarCombo_1 = new CalendarCombo(composite_5, SWT.NONE);
		calendarCombo_1.setBounds(40, 111, 115, 25);

		Label label = new Label(composite_5, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(102, 42, 2, 64);

		Label lblNewLabel = new Label(composite_5, SWT.NONE);
		lblNewLabel.setBounds(30, 61, 66, 17);
		lblNewLabel.setText("操作时间段");

		Label lblNewLabel_1 = new Label(composite_5, SWT.NONE);
		lblNewLabel_1.setBounds(30, 76, 61, 17);
		lblNewLabel_1.setText("    查询");

		final Combo combo = new Combo(composite_5, SWT.NONE);
		combo.setBounds(40, 218, 115, 25);

		Label lblNewLabel_2 = new Label(composite_5, SWT.NONE);
		lblNewLabel_2.setBounds(30, 195, 115, 17);
		lblNewLabel_2.setText("操作火车对象查询");

		Button btnNewButton = new Button(composite_5, SWT.NONE);
		btnNewButton.setBounds(52, 271, 100, 42);
		btnNewButton.setText("查询");

		Composite composite_6 = new Composite(sashForm_2, SWT.NONE);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));

		table_1 = new Table(composite_6, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableColumn tblclmnNewColumn_1 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_1.setWidth(120);
		tblclmnNewColumn_1.setText("火车号");

		TableColumn tblclmnNewColumn_5 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_5.setWidth(120);
		tblclmnNewColumn_5.setText("修改时间");

		TableColumn tblclmnNewColumn_6 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_6.setWidth(120);
		tblclmnNewColumn_6.setText("相关人员ID");

		TableColumn tblclmnNewColumn_7 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_7.setWidth(120);
		tblclmnNewColumn_7.setText("开始日期");

		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.NONE);
		tableColumn_6.setWidth(130);
		tableColumn_6.setText("结束日期");

		TableColumn tblclmnNewColumn_8 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_8.setWidth(130);
		tblclmnNewColumn_8.setText("状态");

		TableColumn tblclmnNewColumn_9 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_9.setWidth(399);
		tblclmnNewColumn_9.setText("备注/详情");
		sashForm_2.setWeights(new int[] {204, 1143});

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				TableItem ti;
				DBHelper db=new DBHelper();
				String sql="select * from s_daily_report where ";
				List<Object> params=new ArrayList<Object>();
				String start_time=calendarCombo_2.getDateAsString().trim();
				String end__time=calendarCombo_3.getDateAsString().trim();
				String s_name=combo_1.getText().trim();
				if(start_time!=null&&end__time!=null&&!"".equals(start_time)&&!"".equals(end__time)){
					sql+="to_date(times,'YYYY-MM-DD') BETWEEN to_date(?,'YYYY-MM-DD') and to_date(?,'YYYY-MM-DD')";
					params.add(String.valueOf(start_time));
					params.add(String.valueOf(end__time));
				}
				if(s_name!=null&&!"".equals(s_name)){
					sql+="s_name=?";
					params.add(s_name);
				}
				List<Map<String,String>> list=db.finds(sql, params);
				if(list!=null && list.size()>0){
					for(Map map:list){
						ti=new TableItem(table,SWT.NONE);
						ti.setText(new String[]{String.valueOf(map.get("s_id")),String.valueOf(map.get("s_name")),String.valueOf(map.get("times")),String.valueOf(map.get("a_id")),
								String.valueOf(map.get("start_dates")),String.valueOf(map.get("end_dates")),String.valueOf(map.get("defect")),String.valueOf(map.get("note"))
						});
					}
				}
			}
		});

		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				table_1.removeAll();
				TableItem ti;
				DBHelper db=new DBHelper();
				String sql="select * from t_daily_report where ";
				List<Object> params=new ArrayList<Object>();
				String start_time=calendarCombo.getDateAsString().trim();
				String end__time=calendarCombo_1.getDateAsString().trim();
				String t_ID=combo.getText().trim();
				if(start_time!=null&&end__time!=null&&!"".equals(start_time)&&!"".equals(end__time)){
					sql+="to_date(times,'YYYY-MM-DD') BETWEEN to_date(?,'YYYY-MM-DD') and to_date(?,'YYYY-MM-DD')";
					params.add(String.valueOf(start_time));
					params.add(String.valueOf(end__time));
					if(t_ID!=null&&!"".equals(t_ID)){
						sql+=" and t_ID=?";
						params.add(t_ID);
					}
				}else if(t_ID!=null&&!"".equals(t_ID)){
					sql+="t_ID=?";
					params.add(t_ID);
				}
				List<Map<String,String>> list=db.finds(sql, params);
				System.out.println(list);
				if(list!=null &&list.size()>0){
					for(Map map:list){
						ti=new TableItem(table_1,SWT.NONE);
						ti.setText(new String[]{String.valueOf(map.get("t_id")),String.valueOf(map.get("times")),String.valueOf(map.get("a_id")),
								String.valueOf(map.get("start_dates")),String.valueOf(map.get("end_dates")),String.valueOf(map.get("defect")),String.valueOf(map.get("note"))
						});
					}
				}	
			}
		});

	}
}
