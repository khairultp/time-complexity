package com.khairul;

import com.khairul.dictionary.MapService;
import com.khairul.dictionary.impl.MapString;
import com.khairul.graph.Graph;
import com.khairul.graph.GraphBuilder;
import com.khairul.graph.PathService;
import com.khairul.graph.impl.BruteForce;
import com.khairul.graph.model.Edge;
import com.khairul.graph.model.Node;
import com.khairul.payment.DiscountFactory;
import com.khairul.payment.DiscountService;
import com.khairul.payment.model.BillTransaction;
import com.khairul.payment.model.Customer;
import com.khairul.payment.model.CustomerType;
import com.khairul.payment.model.SaleType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {

    static Scanner scanner = new Scanner(System.in);

    private static void welcomeScreen() {
        System.out.println("1. Data Store and Load");
        System.out.println("2. Finding Optimal Path");
        System.out.println("3. Discount Retail Website");
        System.out.print("Choice: ");
    }

    public static void main( String[] args ) {
        welcomeScreen();

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                screen1();
                break;
            case 2:
                screen2();
                break;
            case 3:
                screen3();
                break;
            default:
                break;
        }
        scanner.close();
    }

    private static void screen1() {
        System.out.println("Input text");
        String text = input();
        MapService service = new MapString();
        Map[] load = service.load(text);
        System.out.println("load size: " + load.length);
        String store = service.store(load);
        System.out.println("store: \n" + store);
    }

    private static void screen2() {
        Predicate<String> blankLine = line -> line.equals("");

        System.out.println("Input vertices with weights");
        String text = input().toUpperCase();
        Map<String, Node> nodes = Arrays.stream(text.split("\n"))
        .filter(blankLine.negate())
        .map(w -> {
            String label = w.substring(0, 1);
            int weight = Integer.valueOf(w.substring(1));
            return Node.of(label, weight);
        }).collect(Collectors.toMap(Node::getLabel, node -> node));

        System.out.println("Input edges");
        text = input().toUpperCase();
        Set<Edge> edges = Arrays.stream(text.split("\n"))
        .filter(blankLine.negate())
        .map(w -> {
            String[] split = w.split("->");
            Node from = nodes.get(split[0]);
            Node to = nodes.get(split[1]);
            return Edge.of(from, to);
        }).collect(Collectors.toSet());

        Set<Edge> leafEdge = nodes.values().stream()
                .filter(node -> edges.stream().noneMatch(e -> e.getFrom().equals(node)))
                .map(node -> Edge.of(node, null))
                .collect(Collectors.toSet());

        edges.addAll(leafEdge);

        System.out.println("Input origin vertex");
        text = scanner.nextLine().toUpperCase();
        Node origin = nodes.get(text);

        Graph graph = GraphBuilder.createBy(edges);
        PathService service = new BruteForce(graph);

        int result = service.optimalPath(origin);

        System.out.println("With origin vertex " + origin.getLabel() + ", the output is " + result);
    }

    private static void screen3() {

        Customer customer = null;

        System.out.println("Input sales type \n1. Retail \n2. Grocery");
        int number = scanner.nextInt();

        SaleType saleType = null;
        if (number == 1) {
            saleType = SaleType.RETAIL;

            System.out.println("Input Customer type number \n1. Employee \n2. Affiliate of the store \n3. Normal");
            number = scanner.nextInt();
            CustomerType customerType = null;
            if (number == 1) {
                customerType = CustomerType.EMPLOYEE;
            } else if (number == 2) {
                customerType = CustomerType.AFFILIATE_STORE;
            } else if (number == 3) {
                customerType = CustomerType.NORMAL;
            }

            scanner.nextLine();
            System.out.println("Input customer join date [yyyy-mm-dd]: ");
            String strDate = scanner.nextLine();
            LocalDate joinDate = LocalDate.parse(strDate);
            customer = new Customer(customerType, joinDate);
        } else if (number == 2) {
            saleType = SaleType.GROCERY;
            customer = new Customer(CustomerType.NORMAL, LocalDate.now());
        }

        System.out.println("Input total amount");
        BigDecimal amount = scanner.nextBigDecimal();

        BillTransaction bill = BillTransaction.of(customer, saleType, amount);
        DiscountService result = DiscountFactory.of(bill);

        if (result.number().compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Discount not available");
        } else {
            System.out.println("Discount " + result.number() + "%" + " Save $" + result.saveAmount());
        }
    }

    private static String input() {
        String input;
        String text = "";
        int enter = 0;
        do{
            input = scanner.nextLine();
            if (input.equals("")) enter++;
            else {
                text += "\n" + input;
                enter=0;
            }
        } while(enter < 1);
        return text;
    }

}
