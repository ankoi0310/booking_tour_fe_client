package vn.edu.hcmuaf.fit.travie.invoice.data.service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.edu.hcmuaf.fit.travie.core.handler.domain.HttpResponse;
import vn.edu.hcmuaf.fit.travie.invoice.data.model.Invoice;

public interface InvoiceService {
    @GET("invoice")
    Call<HttpResponse<ArrayList<Invoice>>> getInvoices();
}
