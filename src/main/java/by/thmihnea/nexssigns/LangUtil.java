package by.thmihnea.nexssigns;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.List;
import java.util.stream.Collectors;

public class LangUtil {

    public static List<String> applyAllPlaceholders(List<String> stringList, Material material, int amount) {
        stringList = applyColorCodes(stringList);
        stringList = applyItemNamePlaceholder(stringList, material);
        stringList = applyAmountPlaceholder(stringList, amount);
        return stringList;
    }

    public static String getItemName(Material material) {
        String itemName = material.name().replace("_", " ");
        itemName = itemName.toLowerCase(); itemName = toFirstCharUpperAll(itemName);
        return itemName;
    }

    public static List<String> applyItemNamePlaceholder(List<String> stringList, Material material) {
        return stringList.stream().map(string -> string.replace("{itemName}", getItemName(material))).collect(Collectors.toList());
    }

    public static List<String> applyAmountPlaceholder(List<String> stringList, int amount) {
        return stringList.stream().map(string -> string.replace("{amount}", String.valueOf(amount))).collect(Collectors.toList());
    }

    public static List<String> applyColorCodes(List<String> stringList) {
        return stringList.stream().map(string -> ChatColor.translateAlternateColorCodes('&', string)).collect(Collectors.toList());
    }

    public static String toFirstCharUpperAll(String string) {
        StringBuffer stringBuffer = new StringBuffer(string);
        for (int i = 0; i < stringBuffer.length(); i++)
            if (i == 0 || stringBuffer.charAt(i - 1) == ' ')
                stringBuffer.setCharAt(i, Character.toUpperCase(stringBuffer.charAt(i)));
        return stringBuffer.toString();
    }
}
