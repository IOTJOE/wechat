package com.baolishang.springboot.beans;

import com.baolishang.springboot.utils.SafeUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by hello on 2016/9/22.
 */
public class ContentWithPage<T> {

    private JsonNode content;
    private JsonNode pageParams;
    private ObjectMapper mapper;

    public ContentWithPage(JsonNode content, Page page, Long totalElements, Integer totalPages) {
        mapper = new ObjectMapper();
        setContent(content);
        setPageParams(page, totalElements, totalPages);
    }

    public com.fasterxml.jackson.databind.JsonNode getContent() {
        return content;
    }

    public void setContent(JsonNode content) {
        this.content = content;
    }

    public JsonNode getPageParams() {
        return pageParams;
    }

    private void setPageParams(Page page, Long totalElements, Integer totalPages){
        ObjectNode node = mapper.createObjectNode();
        node.put("totalPage", SafeUtils.getInt(totalPages, 0));
        node.put("currentPage", page.getPageNo());
        node.put("totalRecords", totalElements);
        node.put("pageSize", page.getPageSize());
        this.pageParams = node;
    }

}
