package jpabook.jpashop.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void logic(EntityManager em) {
        Team teamA = new Team();
        teamA.setId(1L);
        teamA.setName("teamA");
        em.persist(teamA);

        Team teamB = new Team();
        teamB.setId(2L);
        teamB.setName("teamB");
        em.persist(teamB);



        Member memberA = new Member();
        memberA.setId(1L);
        memberA.setName("memberA");
        memberA.setTeam(teamA);
        em.persist(memberA);

        Member memberB = new Member();
        memberB.setId(2L);
        memberB.setName("memberB");
        memberB.setTeam(teamA);
        em.persist(memberB);

        Member memberC = new Member();
        memberC.setId(3L);
        memberC.setName("memberC");
        memberC.setTeam(teamB);
        em.persist(memberC);

        Member memberD = new Member();
        memberD.setId(4L);
        memberD.setName("memberD");
        memberD.setTeam(teamB);
        em.persist(memberD);


        em.flush();
        em.clear();

        Team findTeamAInsertMemberE = em.find(Team.class, 1L);

        Member memberE = new Member();
        memberE.setId(5L);
        memberE.setName("memberE");
        em.persist(memberE);

        findTeamAInsertMemberE.getMemberList().add(memberE);

        em.flush();
        em.clear();

        Team findTeamA = em.find(Team.class, 1L);

        for (Member m : findTeamA.getMemberList()) {
            System.out.println("teamA Member = " + m.getName());
        }
        
        Team findTeamB = em.find(Team.class, 2L);
        
        for (Member m : findTeamB.getMemberList()) {
            System.out.println("teamB Member = " + m.getName());
        }


        Member findMemberA = em.find(Member.class, 1L);
        System.out.println("findMemberA.getTeam().getName() = " + findMemberA.getTeam().getName());


    }
}
