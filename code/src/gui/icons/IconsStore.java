package gui.icons;

/*
<div>Icônes conçues par
<a href="https://www.flaticon.com/fr/auteurs/alfredo-hernandez" title="Alfredo Hernandez">Alfredo Hernandez</a>
from <a href="https://www.flaticon.com/fr/"
title="Flaticon">www.flaticon.com</a>
</div>
 */

import gui.components.tools.style.STG;
import javafx.scene.image.ImageView;

import java.net.URL;

public class IconsStore extends ImageView {

    /*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
    
    public IconsStore(URL url, boolean ratio) {
	    super(String.valueOf(url));
	    setPreserveRatio(ratio);
    }
    
    public IconsStore(URL url) {
	    super(String.valueOf(url));
	    setFitWidth(STG.BTN_SIZE20);
	    setPreserveRatio(true);
    }
	
	public IconsStore(URL url, double width) {
		super(String.valueOf(url));
		setFitWidth(width);
		setPreserveRatio(true);
	}
    
    public IconsStore(URL url, double width, double height) {
        super(String.valueOf(url));
	    ImageView image = imgViewToF(url);
	    setFitWidth(width);
	    setFitHeight(height);
    }

    private static final String path_uiwindow = "resources/uiwindow/";
    private static final String path_uisimulation = "resources/uisimulation/";
    private static final String path_pattern = "resources/pattern/";

	/*------------------------------*|
	|*			  LOADER			*|
	|*------------------------------*/

	// urls
    public static URL default_url = IconsStore.class.getResource(path_uiwindow + "default.png");
    public static URL defaultview_url = IconsStore.class.getResource(path_uiwindow + "default_view.png");
    public static URL minus_url = IconsStore.class.getResource(path_uiwindow + "minus_view.png");
    public static URL plus_url = IconsStore.class.getResource(path_uiwindow + "credits.png");
    public static URL quit_url = IconsStore.class.getResource(path_uiwindow + "quit.png");
    public static URL minimize_url = IconsStore.class.getResource(path_uiwindow + "minimize.png");
    public static URL he_arc_logo = IconsStore.class.getResource(path_uiwindow + "he_arc_logo.png");
    
    public static URL start_url = IconsStore.class.getResource(path_uisimulation + "play.png");
    public static URL pause_url = IconsStore.class.getResource(path_uisimulation + "particles.png");
    public static URL reset_url = IconsStore.class.getResource(path_uisimulation + "delete.png");
	public static URL reset_zoom_url = IconsStore.class.getResource(path_uisimulation + "resetzoom.png");
	public static URL reset = IconsStore.class.getResource(path_uisimulation + "reset.png");
	public static URL credits_url = IconsStore.class.getResource("resources/credits.html");

		//pattern
	public static URL all_zeros_url = IconsStore.class.getResource(path_pattern + "all_zeros.png");
	public static URL alternate_url = IconsStore.class.getResource(path_pattern + "vertical_cut.png");
	public static URL horizontal_cut_url = IconsStore.class.getResource(path_pattern + "horizontal_cut.png");
	public static URL vertical_cut_url = IconsStore.class.getResource(path_pattern + "alternate.png");
	public static URL rotation_url = IconsStore.class.getResource(path_pattern + "rotation.png");
	

	/*------------------------------------------------------------------*|
	|*							Methods Private		    				*|
	|*------------------------------------------------------------------*/

    private static ImageView imgViewToF(URL url) {
        assert url != null;
        return new ImageView(url.toExternalForm());
    }
	
	
}
