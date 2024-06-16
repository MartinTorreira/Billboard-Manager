package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.BillboardItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static es.udc.paproject.backend.rest.dtos.SessionConversor.toSessionDateDto;
import static es.udc.paproject.backend.rest.dtos.SessionConversor.toSessionDto;

public class BillboardItemConversor {

    private BillboardItemConversor() {}

    public final static BillboardItemDto toBillboardItemDto(BillboardItem billboardItem) {
        List<SessionDateDto> items = billboardItem.getSessionList().stream().map(i -> toSessionDateDto(i)).collect(Collectors.toList());
        items.sort(Comparator.comparing(SessionDateDto::getDateTime));
        return new BillboardItemDto(billboardItem.getId(), billboardItem.getMovie().getId(), billboardItem.getMovie().getTitle(), items);
    }

    public final static List<BillboardItemDto> toBillboardItemDtos(List<BillboardItem> billboard) {
        return billboard.stream().map(c -> toBillboardItemDto(c)).collect(Collectors.toList());
    }
}
