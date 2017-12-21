package pm_views.flows.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shrikanth on 12/21/17.
 */

public class TabContainerData {

    public String containerTitle;
    public List<TabData> tabData;

    public TabContainerData() {
        this.containerTitle = "My Title";
        tabData = new ArrayList<>();
    }
    public int getTabsCount(){
        return tabData != null ? tabData.size() : 0;
    }

    public TabData getTabData(int position){
        return tabData.get(position);
    }

    public void addTabData(TabData data){
        tabData.add(data);
    }
    public static class TabData{
        String title;
        List<String> strings;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getStrings() {
            return strings;
        }

        public void setStrings(List<String> colors) {
            this.strings = colors;
        }
    }
}
