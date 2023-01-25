package se.jensenyh.javacourse.saltmerch.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.jensenyh.javacourse.saltmerch.backend.model.CartItem;

@Repository
public class CartRepository
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<CartItem> selectAllItems() {

        var sql = "SELECT * FROM cart_items";

        RowMapper<CartItem> rm = (rs, rowNum) -> new CartItem(
                rs.getInt("product_id"),
                rs.getString("title"),
                rs.getString("color"),
                rs.getString("size"),
                rs.getString("preview_image"),
                rs.getInt("quantity"));
        return jdbcTemplate.query(sql, rm);
    }
    public int insertOrIncrementItem(CartItem item) {

        var lowerStockSql = """
                UPDATE sizes
                SET stock = stock - 1
                WHERE id = (
                    SELECT s.id FROM sizes AS s
                    INNER JOIN variants AS v
                    ON v.id = s.variant_id
                    WHERE product_id = (:pid) AND color_name = (:color) AND size = (:size));
                """;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pid", item.productId);
        paramMap.put("size", item.size);
        paramMap.put("color", item.color);
        
        int curQty = itemQuantity(item);
        System.out.println("curQty = " + curQty);
        String sql = "";
        if (curQty < 0)
        {

            sql = """
                    INSERT INTO cart_items (product_id, title, color, size, quantity, preview_image)
                    VALUES ((:pid), (:title), (:color), (:size), 1, (:img));
                    """ + lowerStockSql;
            paramMap.put("title", item.title);
            paramMap.put("img", item.previewImage);
        }
        else if (curQty == 0)
            return -2; // edge case, item in cart has qty 0
        else
        {
            // increment quantity
            sql = """
                        UPDATE cart_items
                        SET quantity = quantity + 1
                        WHERE product_id = (:pid) AND color = (:color) AND size = (:size);
                    """ + lowerStockSql;
        }
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, paramMap);
    }
    
    public int deleteOrDecrementItem(CartItem item) {

        var restockSql = """
                UPDATE sizes
                SET stock = stock + 1
                WHERE id = (
                    SELECT s.id FROM sizes AS s
                    INNER JOIN variants AS v
                    ON v.id = s.variant_id
                    WHERE product_id = (:pid) AND color_name = (:color) AND size = (:size));
                """;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pid", item.productId);
        paramMap.put("size", item.size);
        paramMap.put("color", item.color);
        
        int curQty = itemQuantity(item);
        String sql;
        if (curQty < 0)
        {
            return -2;
        }
        else if (curQty < 2)
        {

            sql = """
                    DELETE FROM cart_items
                    WHERE product_id = (:pid) AND color = (:color) AND size = (:size);
                    """ + restockSql;
        }
        else
        {

            sql = """
                    UPDATE cart_items
                    SET quantity = quantity - 1
                    WHERE product_id = (:pid) AND color = (:color) AND size = (:size);
                    """ + restockSql;
        }
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, paramMap);
    }
    @Transactional
    public void deleteAllItems(boolean restock) {

        List<CartItem> cartItems = selectAllItems();
        for (CartItem item : cartItems)
        {
            var sql = """
                    DELETE FROM cart_items
                    WHERE product_id = (:pid) AND size = (:size) AND color = (:color);
                    """ + (restock ? """
                    UPDATE sizes
                    SET stock = stock + (:qty)
                    WHERE id = (
                    	SELECT s.id FROM sizes AS s
                    	INNER JOIN variants AS v
                    	ON v.id = s.variant_id
                    	WHERE product_id = (:pid) AND size = (:size) AND color_name = (:color));
                    """ : "");
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("pid", item.productId);
            paramMap.put("size", item.size);
            paramMap.put("color", item.color);
            paramMap.put("qty", item.quantity);
            int res = new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, paramMap);
            System.out.println("res = " + res);
        }
    }

    private int itemQuantity(CartItem item) {

        RowMapper<Integer> rm = (rs, rowNum) -> rs.getInt("quantity");
        var sql = """
                SELECT quantity
                FROM cart_items
                WHERE product_id = ? AND color = ? AND size = ?
                """;
        List<Integer> quantityList = jdbcTemplate.query(sql, rm, item.productId,
                                                        item.color, item.size);
        return quantityList.size() > 0 ? quantityList.get(0) : -10;
    }
}
