package com.example.item.service.http.controllers.securestock;

import com.example.item.service.app.application.interactors.securestock.issue.SecureStockIssueInteractor;
import com.example.item.service.http.models.securestock.post.ItemAndNr;
import com.example.item.service.http.models.securestock.post.SecureStockPostRequest;
import com.example.item.service.http.models.securestock.post.SecureStockPostResponse;
import com.example.item.shared.domain.models.item.ItemId;
import com.example.order.shared.application.domain.models.order.OrderId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/secure-stocks")
public class SecureStockController {
    private final SecureStockIssueInteractor issueInteractor;

    public SecureStockController(SecureStockIssueInteractor issueInteractor) {
        this.issueInteractor = issueInteractor;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SecureStockPostResponse post(@RequestBody SecureStockPostRequest request) {
        var orderId = OrderId.parseFromString(request.getOrderId())
                .either(it -> {
                    throw new SecureStockControllerExceptions.PostException(it);
                }, it -> it);

        var items = request.getItems().stream()
                .collect(
                        Collectors.toMap(
                                itemAndNr -> ItemId.parseFromString(
                                        itemAndNr.getItemId()).either(error -> {
                                    throw error;
                                }, it -> it), ItemAndNr::getNr
                        )
                );

        var secureStockId = issueInteractor.handle(orderId, items);

        return new SecureStockPostResponse(secureStockId.asString());
    }
}
