package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.dao.TrainDBH;
import com.lcl.my12306.dao.TraininfoDBH;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TicketPrice extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TicketPrice(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(TicketPrice.class, "/image/b11.jpg"));
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label.setBounds(21, 26, 53, 22);
		label.setText("出发地");
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(80, 25, 88, 25);
		
		Label label_1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(186, 36, 64, 2);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_2.setBounds(267, 26, 53, 22);
		label_2.setText("目的地");
		
		final Combo combo_1 = new Combo(composite, SWT.NONE);
		combo_1.setBounds(326, 25, 88, 25);
		
		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button.setBounds(522, 23, 80, 27);
		button.setText("查询");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(124);
		tableColumn.setText("车次");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(125);
		tableColumn_1.setText("出发站");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(124);
		tableColumn_2.setText("目的站");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(125);
		tableColumn_3.setText("软卧票价");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(124);
		tableColumn_4.setText("硬卧票价");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(124);
		tableColumn_5.setText("硬座票价");
		sashForm.setWeights(new int[] {75, 384});

		DBHelper db=new DBHelper();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		String sql="select s_name from station";
		list=db.finds(sql, null);
	    for(int i=0;i<list.size();i++){
	    	combo.add(list.get(i).get("s_name"));
	    	combo_1.add(list.get(i).get("s_name"));
	    }
	    
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				TableItem ti;
				TraininfoDBH traininfoDBH=new TraininfoDBH();
				String t_init=combo.getText().trim();
				String t_dest=combo_1.getText().trim();
				List<Map<String,String>> list=new ArrayList<Map<String,String>>();
				list=traininfoDBH.find(t_init, t_dest);
				for(Map map: list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf( map.get("t_id")),String.valueOf(t_init),
							String.valueOf(t_dest),String.valueOf(map.get("sum(ttt.soft_price)")),String.valueOf(map.get("sum(ttt.hard_price)")),
							String.valueOf(map.get("sum(ttt.seat_price)"))});
				}
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
