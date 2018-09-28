package ec;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.BuyDAO;
import dao.ItemDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//購入日時	配送方法	合計金額
		//商品名	単価
		//getBuyDataBeansByBuyId(int buyId) →BUYIDから合計金額、配送方法、購入日時を取得する

		try {

			String buyId = request.getParameter("buy_id");


		BuyDataBeans resultBDB = BuyDAO.getBuyDataBeansByBuyId(buyId);
		request.setAttribute("resultBDB", resultBDB);

		List<ItemDataBeans> idbList = ItemDAO.getItemDataBeansBybuyId(buyId);
		request.setAttribute("idbList", idbList);


		request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("Error");
		}
	}

}
