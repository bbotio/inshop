package com.inshop.filters;

import com.inshop.dao.ShopDao;
import com.inshop.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by akornev on 14/10/15.
 */
@WebFilter
public class ShopDetectionFilter implements Filter {

    @Autowired
    private ShopDao shopDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final String hostName = req.getHeader("Host");

        //final Shop shop = shopDao.getShopByDomain(hostName);
        final Shop shop = shopDao.getShopByDomain("books.inshop.yt");

        if (shop == null) {
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            // TODO: fix it
            httpResponse.sendRedirect("http://inshop.yt");
            return;
        }

        final HttpSession session = req.getSession();
        session.setAttribute("shop", shop);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
