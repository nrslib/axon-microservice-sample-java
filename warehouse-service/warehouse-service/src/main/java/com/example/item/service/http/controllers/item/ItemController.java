package com.example.item.service.http.controllers.item;

import com.example.item.service.app.application.adaptor.aggregates.item.ItemAggregateProtocol;
import com.example.item.service.app.application.interactors.item.create.ItemCreateInteractor;
import com.example.item.service.http.models.item.post.ItemPostRequest;
import com.example.item.service.http.models.item.post.ItemPostResponse;
import com.example.item.service.http.models.item.stock.ItemStockRequest;
import com.example.item.shared.domain.models.item.ItemId;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(description = "Item API", name = "Item API")
@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final CommandGateway commandGateway;
    private final ItemCreateInteractor createInteractor;

    public ItemController(CommandGateway commandGateway, ItemCreateInteractor createInteractor) {
        this.commandGateway = commandGateway;
        this.createInteractor = createInteractor;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemPostResponse post(@RequestBody ItemPostRequest request) {
        var reply = createInteractor.handle(request.getName(), request.getNr());

        return new ItemPostResponse(
                reply.getSummary().getItemId().getValue(),
                reply.getSummary().getName(),
                reply.getSummary().getNr()
        );
    }

    @PostMapping("/{itemId}/stock")
    public void stock(@PathVariable("itemId") @com.example.item.service.http.constraints.UUID String rawItemId, @RequestBody ItemStockRequest request) {
        var itemId = new ItemId(UUID.fromString(rawItemId));

        var command = new ItemAggregateProtocol.StockItem(itemId, request.getNr());
        commandGateway.sendAndWait(command);
    }
}
