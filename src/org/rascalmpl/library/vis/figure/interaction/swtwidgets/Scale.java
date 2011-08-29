package org.rascalmpl.library.vis.figure.interaction.swtwidgets;


import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.rascalmpl.library.vis.figure.interaction.MouseOver;
import org.rascalmpl.library.vis.properties.PropertyManager;
import org.rascalmpl.library.vis.properties.PropertyValue;
import org.rascalmpl.library.vis.swt.IFigureConstructionEnv;
import org.rascalmpl.library.vis.util.vector.Dimension;
import org.rascalmpl.values.ValueFactoryFactory;

public class Scale extends SWTWidgetFigureWithSingleCallBack<org.eclipse.swt.widgets.Scale>{

	int selection;
	PropertyValue<Integer> low, high,selected;
	public Scale(IFigureConstructionEnv env, Dimension major, PropertyValue<Integer> low, PropertyValue<Integer> high,  PropertyValue<Integer> selected,IValue callback,
			PropertyManager properties) {
		super(env, callback, properties);
		widget = makeWidget(env.getSWTParent(), major, env);
		widget.setVisible(false);
		this.selected = selected;
		this.low = low;
		this.high = high;
	}
	

	org.eclipse.swt.widgets.Scale makeWidget(Composite comp, Dimension major, IFigureConstructionEnv env) {
		int swtConstant = 0;
		switch(major){
		case X: swtConstant = SWT.HORIZONTAL; break;
		case Y: swtConstant = SWT.VERTICAL; break;
		}
		org.eclipse.swt.widgets.Scale result = new org.eclipse.swt.widgets.Scale(comp,swtConstant);
		result.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doCallback();
			}
		});
		return result;
	}
	

	public void initElem(IFigureConstructionEnv env, MouseOver mparent, boolean swtSeen, boolean visible){
		widget.setMinimum(low.getValue());
		widget.setSelection(selected.getValue());
		widget.setMaximum(high.getValue());
		super.initElem(env, mparent, swtSeen, visible);
	}
	

	@Override
	void executeCallback() {
		cbenv.executeRascalCallBackSingleArgument(callback, TypeFactory
				.getInstance().integerType(), ValueFactoryFactory.getValueFactory().integer(widget.getSelection()));
	}


}