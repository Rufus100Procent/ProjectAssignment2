package se.jensenyh.javacourse.saltmerch.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import se.jensenyh.javacourse.saltmerch.backend.model.CartItem;
import se.jensenyh.javacourse.saltmerch.backend.model.ColorVariant;
import se.jensenyh.javacourse.saltmerch.backend.model.Product;
import se.jensenyh.javacourse.saltmerch.backend.model.SizeContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tests {

    protected final ObjectMapper OM = new ObjectMapper();

    protected final static int prod1_Id = 1;
    protected final static int prod2_Id = 2;
    protected final static int prod3_Id = 3;

    protected final static String prod1_Category = "hats";
    protected final static String prod2_Category = "bags";
    protected final static String prod3_Category = "jackets";

    protected final static String prod1_Title = "Some hat 1";
    protected final static String prod2_Title = "Some bag 1";
    protected final static String prod3_Title = "Some jacket 1";

    protected final static String prod1_Description = "This is some hat 1";
    protected final static String prod2_Description = "This is some bag 1";
    protected final static String prod3_Description = "This is some jacket 1";

    protected final static List<ColorVariant> prod1_ColorVariants = new ArrayList<>();
    protected final static List<ColorVariant> prod2_ColorVariants = new ArrayList<>();
    protected final static List<ColorVariant> prod3_ColorVariants = new ArrayList<>();

    protected final static List<Product> products = new ArrayList<>();

    protected final static List<Product> prod1_Hats = new ArrayList<>();
    protected final static List<Product> prod2_Bags = new ArrayList<>();
    protected final static List<Product> prod3_Jackets = new ArrayList<>();

    protected final static String colV1_Color = "Orange";
    protected final static String colV2_Color = "Black";
    protected final static String colV3_Color = "Yellow";

    protected final static String image1 = "";
    protected final static String image2 = "";
    protected final static String image3 = "";

    protected final static List<String> colV1_Images = new ArrayList<>();
    protected final static List<String> colV2_Images = new ArrayList<>();
    protected final static List<String> colV3_Images = new ArrayList<>();

    protected final static String sizC1_Size = "S";
    protected final static String sizC2_Size = "M";
    protected final static String sizC3_Size = "L";

    protected final static List<SizeContainer> colV1_Sizes = new ArrayList<>();
    protected final static List<SizeContainer> colV2_Sizes = new ArrayList<>();
    protected final static List<SizeContainer> colV3_Sizes = new ArrayList<>();

    protected final static int sizC1_Stock = 1;
    protected final static int sizC2_Stock = 2;
    protected final static int sizC3_Stock = 3;

    protected final static SizeContainer sizC1 = new SizeContainer(sizC1_Size, sizC1_Stock);
    protected final static SizeContainer sizC2 = new SizeContainer(sizC2_Size, sizC2_Stock);
    protected final static SizeContainer sizC3 = new SizeContainer(sizC3_Size, sizC3_Stock);

    protected static ColorVariant colV1;
    protected static ColorVariant colV2;
    protected static ColorVariant colV3;

    protected static Product prod1;
    protected static Product prod2;
    protected static Product prod3;
    protected  static CartItem cartItem1;
    protected final static List<CartItem> cartItems = new ArrayList<>();


    @BeforeAll
    protected static void beforeAll() throws Exception {

        Collections.addAll(colV1_Sizes,sizC1, sizC2, sizC3);
        Collections.addAll(colV2_Sizes,sizC1, sizC2, sizC3);
        Collections.addAll(colV3_Sizes,sizC1, sizC2, sizC3);

        Collections.addAll(colV1_Images, image1, image2, image3);
        Collections.addAll(colV2_Images, image1, image2, image3);
        Collections.addAll(colV3_Images, image1, image2, image3);

        colV1 = new ColorVariant(colV1_Color, colV1_Images, colV1_Sizes);
        colV2 = new ColorVariant(colV2_Color, colV2_Images, colV2_Sizes);
        colV3 = new ColorVariant(colV3_Color, colV3_Images, colV3_Sizes);

        Collections.addAll(prod1_ColorVariants, colV1, colV2, colV3);
        Collections.addAll(prod2_ColorVariants, colV1, colV2, colV3);
        Collections.addAll(prod3_ColorVariants, colV1, colV2, colV3);

        prod1 = new Product(prod1_Id, prod1_Category, prod1_Title, prod1_Description, prod1_ColorVariants);
        prod2 = new Product(prod2_Id, prod2_Category, prod2_Title, prod2_Description, prod2_ColorVariants);
        prod3 = new Product(prod3_Id, prod3_Category, prod3_Title, prod3_Description, prod3_ColorVariants);

        Collections.addAll(products, prod1, prod2, prod3);

        Collections.addAll(prod1_Hats, prod1);
        Collections.addAll(prod2_Bags, prod2);
        Collections.addAll(prod3_Jackets, prod3);

        cartItem1 = new CartItem(prod1_Id, prod1_Title, colV1_Color, sizC1_Size, image1);
        Collections.addAll(cartItems, cartItem1);
    }

}
