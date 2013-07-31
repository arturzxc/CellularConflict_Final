/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import controllers.Settings;
import core.physics.Point2D;
import java.util.*;

/**
 * Create viruses and add them to this list.
 * @author Artur Krzynowek
 */
public class VirusFactory implements List<Virus> {

    private List<Virus> viruses = new ArrayList<>();
    private Random gen = new Random();

    public void tick() {
        for (Virus v : viruses) {
            v.tick();
        }
    }

    public void generateViruses(int number, int color, int type, int radius) {
        for (int i = 0; i < number; i++) {
            Point2D p = new Point2D(
                    gen.nextInt(Settings.getInstance().getGridWidth()),
                    gen.nextInt(Settings.getInstance().getGridHeight()));
            viruses.add(new Virus(color, type, p, radius));
        }
    }

    @Override
    public int size() {
        return viruses.size();
    }

    @Override
    public boolean isEmpty() {
        return viruses.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return viruses.contains(o);
    }

    @Override
    public Iterator iterator() {
        return viruses.iterator();
    }

    @Override
    public Object[] toArray() {
        return viruses.toArray();
    }

    @Override
    public Object[] toArray(Object[] a) {
        return viruses.toArray(a);
    }

    @Override
    public boolean add(Virus e) {
        try {
            return viruses.add(e);
        } catch (Exception exc) {
            System.out.println("cannot add anything but viruses");
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return viruses.remove(o);
    }

    @Override
    public boolean containsAll(Collection c) {
        return viruses.containsAll(c);
    }

    @Override
    public boolean addAll(Collection c) {
        return viruses.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return viruses.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection c) {
        return viruses.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection c) {
        return viruses.retainAll(c);
    }

    @Override
    public void clear() {
        viruses.clear();
    }

    @Override
    public Virus get(int index) {
        return viruses.get(index);
    }

    @Override
    public Virus set(int index, Virus element) {
        return viruses.set(index, element);
    }

    @Override
    public void add(int index, Virus element) {
        viruses.add(index, element);
    }

    @Override
    public Virus remove(int index) {
        return viruses.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return viruses.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return viruses.lastIndexOf(o);
    }

    @Override
    public ListIterator listIterator() {
        return viruses.listIterator();
    }

    @Override
    public ListIterator listIterator(int index) {
        return viruses.listIterator(index);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return viruses.subList(fromIndex, toIndex);
    }
}
