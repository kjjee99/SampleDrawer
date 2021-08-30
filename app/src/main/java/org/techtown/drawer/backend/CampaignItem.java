package org.techtown.drawer.backend;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignItem {
    private String title;
    private String content;

    @Builder
    public CampaignItem(String title, String content){
        this.title = title;
        this.content = content;
    }
}
