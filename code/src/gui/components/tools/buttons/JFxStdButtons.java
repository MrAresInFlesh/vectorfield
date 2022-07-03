package gui.components.tools.buttons;

import gui.components.tools.JFxLabel;
import gui.components.tools.style.STG;
import gui.icons.IconsStore;
import javafx.scene.layout.HBox;


public class JFxStdButtons extends HBox {

	/*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxStdButtons (double spacing, IconsStore start, IconsStore pause, IconsStore reset) {
		super(spacing);
		btn_start = new JFxIconButton(start, STG.BTN_SIZE20*2);
		btn_pause = new JFxIconButton(pause, STG.BTN_SIZE20*2);
		btn_reset = new JFxIconButton(reset, STG.BTN_SIZE20*2);
		geometry();
		control();
		appearance();
	}
	
	/*------------------------------------------------------------------*|
	|*							Public Methods		    				*|
	|*------------------------------------------------------------------*/
	
	public JFxIconButton getBtn_start () {
		return btn_start;
	}
	public JFxIconButton getBtn_pause () {
		return btn_pause;
	}
	public JFxIconButton getBtn_reset () {
		return btn_reset;
	}

	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry() {
		JFxLabel lbl_start = new JFxLabel(btn_start);
		JFxLabel lbl_pause = new JFxLabel(btn_pause);
		JFxLabel lbl_reset = new JFxLabel(btn_reset);
		
		HBox hBox = new HBox();
		hBox.getChildren().addAll(lbl_start, lbl_pause, lbl_reset);
		getChildren().addAll(hBox);
	}
	private void control() {
	}
	private void appearance() {
	
	}
	
	// tools
	private final JFxIconButton btn_start;
	private final JFxIconButton btn_pause;
	private final JFxIconButton btn_reset;
}
