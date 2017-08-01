package com.sdcuike.spring.extend.web.util;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by beaver on 2017/8/1.
 */
public final class PageableUtils {
    
    /**
     * 传入分页信息及数据列表，得到当前页数据，排序只支持一个属性排序
     *
     * @param pageable 分页信息
     * @param list     需要分页的数据列表
     * @param <T>
     * @return Page<T>  当前页
     */
    public static <T> Page<T> getCurrentPage(final Pageable pageable, final List<T> list) {
        final PagedListHolder pagedListHolder = new PagedListHolder(list);
        
        pagedListHolder.setPage(pageable.getPageNumber());
        pagedListHolder.setPageSize(pageable.getPageSize());
        
        final List<MutableSortDefinition> sortDefinitions = new ArrayList<>();
        
        final Sort pageableSort = pageable.getSort();
        if (pageableSort != null) {
            Iterator<Order> iterator = pageableSort.iterator();
            while (iterator.hasNext()) {
                final Order order = iterator.next();
                MutableSortDefinition sortDefinition = new MutableSortDefinition();
                sortDefinition.setProperty(order.getProperty());
                sortDefinition.setAscending(order.isAscending());
                sortDefinitions.add(sortDefinition);
            }
        }
        
        sort(pagedListHolder, sortDefinitions);
        pagedListHolder.setPage(pageable.getPageNumber());
        Page<T> page = new PageImpl<>(pagedListHolder.getPageList(), pageable, list.size());
        
        return page;
        
    }
    
    private static void sort(final PagedListHolder pagedListHolder,
                             final List<MutableSortDefinition> sortDefinitions) {
        for (MutableSortDefinition definition : sortDefinitions) {
            pagedListHolder.setSort(definition);
            pagedListHolder.resort();
        }
        
    }
    
    public static void main(String[] args) {
        
        final List<Person> personList = Arrays.asList(
                Person.of("cui", 188),
                Person.of("cui", 8888),
                Person.of("ac", 22),
                Person.of("bc", 66));
        
        
        Sort sortName = new Sort(Direction.ASC, "name");
        Sort sortAge = new Sort(Direction.ASC, "age");
        
        
        PageRequest pageable = new PageRequest(1, 2, sortAge);
        
        final Page<Person> currentPage = getCurrentPage(pageable, personList);
        System.out.println(out(currentPage));
        System.out.println("isLast:"+currentPage.isLast());
        System.out.println("isFirst:"+currentPage.isFirst());
    }
    
    private static String out(Page<?> currentPage) {
        StringBuilder sb = new StringBuilder();
        sb.append("TotalElements:").append(currentPage.getTotalElements()).append("\n");
        sb.append("TotalPages:").append(currentPage.getTotalPages()).append("\n");
        sb.append("Number:").append(currentPage.getNumber()).append("\n");
        sb.append("NumberOfElements:").append(currentPage.getNumberOfElements()).append("\n");
        sb.append("Content:").append(currentPage.getContent()).append("\n");
        sb.append("Sort:").append(currentPage.getSort()).append("\n");
        
        return sb.toString();
    }
    
    private static class Person {
        private String name;
        private int age;
        
        public static Person of(String name, int age) {
            Person person = new Person();
            person.setName(name);
            person.setAge(age);
            return person;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public int getAge() {
            return age;
        }
        
        public void setAge(int age) {
            this.age = age;
        }
        
        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
