package com.job.finalproject.finalproject.service;

import com.job.finalproject.finalproject.persistance.entity.Director;
import com.job.finalproject.finalproject.persistance.entity.Genre;
import com.job.finalproject.finalproject.persistance.entity.Movie;
import com.job.finalproject.finalproject.persistance.entity.Rating;
import com.job.finalproject.finalproject.persistance.entity.Reviewer;
import com.job.finalproject.finalproject.persistance.entity.TitleType;
import com.opencsv.CSVReader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class LoadFileService {


    private Set<Director> allDirectorList=new HashSet<>();
    private Set<Movie> allMovieList=new HashSet<>();
    private Set<Genre> allGenreList=new HashSet<>();
    private Map<String, Genre> genreMap = new HashMap<>();
    private Map<String, Director> directorMap=new HashMap<>();
    private Map<String, TitleType> titleTypeMap=new HashMap<>();
    private Map<String, Movie> movieMap=new HashMap<>();
    private Map<String, Reviewer> reviewerMap=new HashMap<>();
    private Set<Rating> allRatingList=new HashSet<>();
    private Set<Reviewer> allReviewerList=new HashSet<>();
    private Set<TitleType> allTitleTypeList=new HashSet<>();

    private List<String> pathList=new ArrayList<>();

    public void importFile() throws IOException {

        long startTime = System.nanoTime();
        paths();
        for(String iter: pathList) {
               csvToTable(iter);

        }

        addDirector(allDirectorList);
        addGenre(allGenreList);
        addTitleType(allTitleTypeList);
        addMovie(allMovieList);
        addReviewer(allReviewerList);
        addRating(allRatingList);

        final long duration = System.nanoTime() - startTime;
        final double seconds = ((double)duration / 1000000000);
        System.out.println(new DecimalFormat("#.##########").format(seconds)+" seconds" );

    }


   /* private List<Movie> csvToMovie(String path) throws IOException {
        CsvToBean<Movie> csvToBeanMovie = new CsvToBean<Movie>();

        Map<String, String> columnMappingMovie = new HashMap<String, String>();
        columnMappingMovie.put("position", "id");
        columnMappingMovie.put("const", "code");
        columnMappingMovie.put("IMDb Rating","imdbrating");
        columnMappingMovie.put("Num. Votes","numvotes");
        columnMappingMovie.put("Release Date (month/day/year)","releasdate");
        columnMappingMovie.put("Runtime (mins)","runtime");
        columnMappingMovie.put("Title","title");
        columnMappingMovie.put("URL","url");

        HeaderColumnNameTranslateMappingStrategy<Movie> strategyMovie = new HeaderColumnNameTranslateMappingStrategy<Movie>();
        strategyMovie.setType(Movie.class);
        strategyMovie.setColumnMapping(columnMappingMovie);

        List<Movie> movieList=new ArrayList<>();
        CSVReader reader = new CSVReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path)));
        reader.skip(1);
        String[] values;
        while((values=reader.readNext())!=null){
            Movie movie=new Movie();
            movie.setCode(values[1]);
            movie.setTitle(values[5]);
                if(!values[9].equals("")) {
                    movie.setImdbrating(new BigDecimal(values[9]));
                }
                else {
                    movie.setImdbrating(new BigDecimal(0));
                }
            if(!values[10].equals("")) {
                movie.setRuntime(Integer.parseInt(values[10]));
            }
            else {
                movie.setRuntime(0);
            }
            movie.setNumvotes(Integer.parseInt(values[13]));
            if(values[14].length()==10) {
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                Date releasdate = null;
                try {
                    releasdate = dateFormat.parse(values[14]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                java.sql.Date date = new java.sql.Date(releasdate.getTime());
                movie.setReleasdate(date);
            }else if(values[14].length()==7){
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM");
                Date releasdate = null;
                try {
                    releasdate = dateFormat.parse(values[14]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                java.sql.Date date = new java.sql.Date(releasdate.getTime());
                movie.setReleasdate(date);
            }
            else {
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");
                Date releasdate = null;
                try {
                    releasdate = dateFormat.parse(values[14]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                java.sql.Date date = new java.sql.Date(releasdate.getTime());
                movie.setReleasdate(date);
            }

            movie.setUrl(values[values.length-1]);
            movieList.add(movie);
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // addMovie(movieList);
        return movieList;
    }*/

    private void csvToTable(String path) throws IOException {

        Set<Director> directorList = new HashSet<>();
        Set<Movie> movieList=new HashSet<>();
        Set<Genre> genreList=new HashSet<>();
        Set<Rating> ratingList=new HashSet<>();
        Set<TitleType> titleTypeList=new HashSet<>();
        List<Reviewer> reviewerList=new ArrayList<>();
        CSVReader reader = new CSVReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path)));

        reader.skip(1);
        String[] values;
        while((values=reader.readNext())!=null) {
            try {
                Integer.parseInt(values[0]); //check if is valid line
                //if valid line continue

                //******************************MOVIE*********************************************************
                Movie movie = new Movie();
                movie.setCode(values[1]);
                if(values[5].matches(".*\\w.*")) {
                    movie.setTitle(values[5]);
                }
                if (!values[9].equals("")) {
                    movie.setImdbrating(new BigDecimal(values[9]));
                } else {
                    movie.setImdbrating(new BigDecimal(0));
                }
                if (!values[10].equals("")) {
                    movie.setRuntime(Integer.parseInt(values[10]));
                } else {
                    movie.setRuntime(0);
                }
                movie.setNumvotes(Integer.parseInt(values[13]));
                if (values[14].length() == 10) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date releasdate = null;
                    try {
                        releasdate = dateFormat.parse(values[14]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    java.sql.Date date = new java.sql.Date(releasdate.getTime());
                    movie.setReleasdate(date);
                } else if (values[14].length() == 7) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                    Date releasdate = null;
                    try {
                        releasdate = dateFormat.parse(values[14]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    java.sql.Date date = new java.sql.Date(releasdate.getTime());
                    movie.setReleasdate(date);
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                    Date releasdate = null;
                    try {
                        releasdate = dateFormat.parse(values[14]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    java.sql.Date date = new java.sql.Date(releasdate.getTime());
                    movie.setReleasdate(date);
                }
                movie.setUrl(values[values.length - 1]);

                //********************************DIRECTOR**********************************************************

                String[] directors=values[7].split(",");
                for(int i=0;i<directors.length;i++){
                    Director director = new Director();
                    if (directors[i].matches(".*\\w.*")) {
                        director.setFullname(directors[i]);
                        directorList.add(director);
                        movie.getDirectorList().add(director);
                    }
                }

                //**********************************GENRES****************************************************
                String[] genres = values[12].split("\\s*(=>|,|\\s)\\s*");
                for (int i = 0; i < genres.length; i++) {
                    Genre item = new Genre();
                    if (genres[i].matches(".*\\w.*")) {
                        item.setType(genres[i]);
                        genreList.add(item);
                        movie.getGenres().add(item);
                    }
                }

                //*********************************END GENRE*********************************************************

                String path1 = path;
                String id = path1.substring(0, path1.length() - 4);


                //*****************************REVIEWER*********************************************************
                Reviewer reviewer2=new Reviewer(setReviewer(path)); //GET USERNAME
                reviewer2.setId(Long.parseLong(id));
                reviewerList.add(reviewer2);
                //********************************END REVIEWER******************************************************
                //********************************TITLE TYPE***************************************************************
                TitleType titleType = new TitleType();
                if(values[6].matches(".*\\w.*")) {
                    titleType.setType(values[6]);
                }
                titleTypeList.add(titleType);
                //*********************************END TITLE TYPE*****************************************************
                movie.getTitleTypeList().add(titleType);
                movieList.add(movie);
                //***********************************END MOVIE****************************************************
                //*******************************RATING******************************************************
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.US);
                Rating rating = new Rating();
                Date createddate = null;
                try {
                    createddate = dateFormat.parse(values[2]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                java.sql.Date date = new java.sql.Date(createddate.getTime());
                rating.setCrated(date);
                rating.setReviewerating(Integer.parseInt(values[8]));
                rating.setMovie(movie);
                rating.setReviewer(reviewer2);
                ratingList.add(rating);
                //*****************************END RATING*********************************************************

            }catch (Exception e){
                System.out.println(Arrays.toString(values));
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        allDirectorList.addAll(directorList);
        allMovieList.addAll(movieList);
        allGenreList.addAll(genreList);
        allRatingList.addAll(ratingList);
        allReviewerList.add(reviewerList.get(0));
        allTitleTypeList.addAll(titleTypeList);

    }
    private Reviewer setReviewer(String path) throws IOException {
        Reviewer reviewer=new Reviewer();
        CSVReader reader = new CSVReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path)));
        String[] values;
        while((values=reader.readNext())!=null) {
            if (values[8].substring(0, values[8].length() - 6).matches(".*\\w.*")) {
                reviewer.setUsername(values[8].substring(0, values[8].length() - 6));
                break;
            }
        }
        return reviewer;
    }

    private void addDirector(Set<Director> directorList) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        directorList=removeDuplicatesDirector(directorList);
        Transaction tx = session.beginTransaction();
//        int count = 0;
        // INSERT DIRECTORY
        for (Director it : directorList) {
                try {
                    session.save(it);
                    directorMap.put(it.getFullname(), it);
                   // count++;
//                    if (count % 20 == 0) {
                        session.flush();
                        session.clear();
//                    }
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Error Director");
                }

        }
        tx.commit();
        session.close();
    }

    private void addMovie(Set<Movie> movieList){

        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
         movieList=removeDuplicatesMovie(movieList);
        int count=0;
        // INSERT MOVIE
        for(Movie movie: movieList) {
            try {
                List<Genre> movieGenres = new ArrayList<>();
                for (Genre genre : movie.getGenres()) {
                    movieGenres.add(genreMap.get(genre.getType()));
                }
                List<Director> movieDirector=new ArrayList<>();
                for(Director director : movie.getDirectorList()){
                    movieDirector.add(directorMap.get(director.getFullname()));
                }
                List<TitleType> movieTitleType=new ArrayList<>();
                for(TitleType titleType : movie.getTitleTypeList()){
                    movieTitleType.add(titleTypeMap.get(titleType.getType()));
                }
                movie.setTitleTypeList(movieTitleType);
                movie.setDirectorList(movieDirector);
                movie.setGenres(movieGenres);
                session.save(movie);
                movieMap.put(movie.getCode(),movie);
               // count++;
               /* if (count % 20 == 0) {*/
                    session.flush();
                    session.clear();
                //}
            }catch (Exception e){
                System.out.println("Error Movie");
            }

        }
        tx.commit();
        session.close();

    }

    private void addGenre(Set<Genre> genreList){

        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        // INSERT GENRE
        for(Genre genre:genreList) {
           try {
               session.save(genre);
               genreMap.put(genre.getType(), genre);
              /* count++;
               if (count % 20 == 0) {*/
                   session.flush();
                   session.clear();
              // }
           }catch (Exception e){
               System.out.println("Error Genre");
           }

        }
        tx.commit();
        session.close();
    }

    private void addRating(Set<Rating> ratingList){

        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //ratingList=removeDuplicatesRating(ratingList);
        int count=0;
        // INSERT RATING
        for(Rating it:ratingList) {
            try {

                Movie movie=movieMap.get(it.getMovie().getCode());
                Reviewer reviewer=reviewerMap.get(it.getReviewer().getUsername());
                it.setReviewer(reviewer);
                it.setMovie(movie);
                it.setReviewerid(reviewer.getId());
                it.setMovieid(movie.getId());
                session.save(it);
                count++;
                if (count % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }catch (Exception e){
                System.out.println("Error Rating");
            }

        }
        tx.commit();
        session.close();

    }
    private void addReviewer(Set<Reviewer> reviewerlist){

        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        reviewerlist=removeDuplicatesReviewer(reviewerlist);
        int count=0;
        // INSERT REVIEWER
        for(Reviewer it: reviewerlist) {
            try {
                session.save(it);
                reviewerMap.put(it.getUsername(),it);
                count++;
                if (count % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            } catch (Exception e) {
                System.out.println("Error Reviewer");
            }
        }
        tx.commit();
        session.close();

    }
    private void addTitleType(Set<TitleType> titleTypeList){

        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        titleTypeList=removeDuplicatesTitleType(titleTypeList);
        int count=0;
        // INSERT TITLE TYPE
        for(TitleType it:titleTypeList) {
           try {
               session.save(it);
               titleTypeMap.put(it.getType(),it);
               count++;
               if (count % 20 == 0) {
                   session.flush();
                   session.clear();
               }
           }catch (Exception e){
               System.out.println("Error TitleType");
           }

        }
        tx.commit();
        session.close();

    }

    private Set<Reviewer> removeDuplicatesReviewer(Set<Reviewer> inputList){
        return inputList.stream().filter(distinctByKey(reviewer -> reviewer.getUsername())).collect(Collectors.toSet());
    }
    private Set<Movie> removeDuplicatesMovie( Set<Movie> inputList ){
        return  inputList.stream().filter(distinctByKey(movie -> movie.getTitle())).filter(distinctByKey(movie -> movie.getCode())).filter(distinctByKey(movie -> movie.getCode())).collect(Collectors.toSet());

    }
    private Set<Director> removeDuplicatesDirector( Set<Director> inputList ){
        return inputList.stream().filter(distinctByKey(director -> director.getFullname().toLowerCase())).collect(Collectors.toSet());

    }

    private Set<TitleType> removeDuplicatesTitleType(Set<TitleType> inputstream){
        return inputstream.stream().filter(distinctByKey(titleType -> titleType.getType())).collect(Collectors.toSet());
    }


    private void paths() {
        File files= new File("C:\\Users\\User\\Desktop\\data");
        List<String> allfiles=Arrays.asList(files.list());
        for(String file:allfiles){
            if(file.endsWith(".csv")){
                pathList.add(file);
            }
        }
      //   System.out.println(pathList);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
