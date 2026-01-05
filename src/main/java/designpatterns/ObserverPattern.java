package designpatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern - defines a one-to-many dependency between objects
 * so that when one object changes state, all its dependents are notified
 */
public class ObserverPattern {
    
    // Subject interface
    interface Subject {
        void attach(Observer observer);
        void detach(Observer observer);
        void notifyObservers();
    }
    
    // Observer interface
    interface Observer {
        void update(String message);
    }
    
    // Concrete Subject
    static class NewsAgency implements Subject {
        private List<Observer> observers = new ArrayList<>();
        private String news;
        
        @Override
        public void attach(Observer observer) {
            observers.add(observer);
            System.out.println("Observer attached");
        }
        
        @Override
        public void detach(Observer observer) {
            observers.remove(observer);
            System.out.println("Observer detached");
        }
        
        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(news);
            }
        }
        
        public void setNews(String news) {
            this.news = news;
            System.out.println("\nBreaking News: " + news);
            notifyObservers();
        }
    }
    
    // Concrete Observers
    static class NewsChannel implements Observer {
        private String name;
        
        public NewsChannel(String name) {
            this.name = name;
        }
        
        @Override
        public void update(String message) {
            System.out.println(name + " received update: " + message);
        }
    }
    
    static class MobileApp implements Observer {
        private String appName;
        
        public MobileApp(String appName) {
            this.appName = appName;
        }
        
        @Override
        public void update(String message) {
            System.out.println(appName + " push notification: " + message);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Example ===\n");
        
        NewsAgency agency = new NewsAgency();
        
        Observer channel1 = new NewsChannel("CNN");
        Observer channel2 = new NewsChannel("BBC");
        Observer app = new MobileApp("NewsApp");
        
        agency.attach(channel1);
        agency.attach(channel2);
        agency.attach(app);
        
        agency.setNews("Major breakthrough in AI technology!");
        
        System.out.println("\nDetaching BBC...");
        agency.detach(channel2);
        
        agency.setNews("Stock market hits new record high!");
    }
}
