package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class HomePage extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public HomePage(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(HomePage.class, "/image/b2.png"));
		setLayout(new FillLayout(SWT.HORIZONTAL));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
