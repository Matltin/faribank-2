package ir.ac.kntu.db;

import ir.ac.kntu.Constance;
import ir.ac.kntu.person.admin.Admin;
import ir.ac.kntu.util.ScannerWrapper;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AdminDB {

    private Set<Admin> admins;

    public AdminDB(Set<Admin> admins) {
        this.admins = admins;
//        admins.add(new Admin("Matin", "Ahmadi", "Rr@138406", "m"));
//        admins.add(new Admin("Reyhane", "MirArabshahi", "Rr@138406", "r"));
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public void removeAdmin(Admin admin) {
        admins.remove(admin);
    }

    public Set<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }

    public Admin findAdmin(String username) {
        for (Admin admin : admins) {
            if (admin.getUserName().equals(username)) {
                return admin;
            }
        }
        return null;
    }

    public boolean doesExist(Admin admin) {
        return admins.contains(admin);
    }

    public void printAdmin() {
        Map<Integer, Admin> map = getMap();
        int size = map.size();
        int valueToDisPlay = Constance.VALUE_TO_DISPLAY;
        if (valueToDisPlay > size) {
            valueToDisPlay = size;
        }
        int currentPosition = 1;
        String inputStr;
        print(1, valueToDisPlay + 1, map);
        do {
            inputStr = ScannerWrapper.getInstance().nextLine();
            switch (inputStr) {
                case "next" -> currentPosition = plus(currentPosition, size, valueToDisPlay, map);
                case "back" -> currentPosition = minus(currentPosition, size, -valueToDisPlay, map);
                case "quit" -> {
                    return;
                }
                default -> System.out.println("invalid input");
            }
        } while (true);
    }

    private Map<Integer, Admin> getMap() {
        Map<Integer, Admin> map = new HashMap<>();
        int counter = 1;
        for (Admin admin : admins) {
            map.put(counter, admin);
            counter++;
        }
        return map;
    }

    private int minus(int currentPosition, int size, int amount, Map<Integer, Admin> map) {
        if (currentPosition + amount < 0) {
            currentPosition = 0;
            print(1, -amount + 1, map);
            voice();
        } else {
            if (currentPosition == size) {
                currentPosition += amount;
            }
            if (currentPosition + amount < 1) {
                currentPosition = 0;
                print(1, -amount + 1, map);
                return currentPosition;
            }
            print(currentPosition + amount, currentPosition + 1, map);
            currentPosition += amount;
        }
        return currentPosition;
    }

    private int plus(int currentPosition, int size, int amount, Map<Integer, Admin> map) {
        if (currentPosition + amount > size) {
            currentPosition = size;
            print(size - amount, size, map);
            voice();
        } else {
            if (currentPosition == 1) {
                currentPosition += amount;
            }
            if (currentPosition + amount > size) {
                currentPosition = size;
                print(size - amount, size, map);
                return currentPosition;
            }
            print(currentPosition, currentPosition + amount, map);
            currentPosition += amount;
        }
        return currentPosition;
    }


    private void print(int first, int second, Map<Integer, Admin> map) {
        for (int i = first; i < second; i++) {
            System.out.println(i + "." + map.get(i).getFirstName() + " " + map.get(i).getLastName());
        }
    }

    private void voice() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File("ding.wav");
                AudioInputStream audioStream = null;
                Clip clip = null;
                try {
                    audioStream = AudioSystem.getAudioInputStream(file);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        assert audioStream != null;
                        assert clip != null;
                        audioStream.close();
                        clip.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
