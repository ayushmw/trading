package com.ayush.trading.services;

import com.ayush.trading.entities.Nifty;
import com.ayush.trading.entities.OptionWeek;
import com.ayush.trading.repositories.NiftyRepository;
import com.ayush.trading.repositories.OptionWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DefaultNiftyManager implements NiftyManager {
    @Autowired
    private NiftyRepository niftyRepository;

    @Autowired
    private OptionWeekRepository optionWeekRepository;

    @Override
    public void populateOptionWeeks() {
        List<Nifty> niftyData = niftyRepository.findAll();
        niftyData.sort(Comparator.comparing(Nifty::getDate));

        var optionWeeks = new ArrayList<OptionWeek>();
        int i = 0;
        while (i < niftyData.size()) {
            var optionWeek = new OptionWeek();
            Nifty openRecord = niftyData.get(i);

            optionWeek.setOpen(openRecord.getOpen());
            optionWeek.setClose(openRecord.getClose());
            optionWeek.setHigh(openRecord.getHigh());
            optionWeek.setLow(openRecord.getLow());
            optionWeek.setStartDate(openRecord.getDate());
            optionWeek.setEndDate(openRecord.getDate());

            i++;
            while (i < niftyData.size() && niftyData.get(i).getDate().isBefore(getNextFriday(openRecord.getDate()))) {
                Nifty currRecord = niftyData.get(i);
                optionWeek.setClose(currRecord.getClose());
                optionWeek.setHigh(Math.max(optionWeek.getHigh(), currRecord.getHigh()));
                optionWeek.setLow(Math.min(optionWeek.getLow(), currRecord.getLow()));
                optionWeek.setEndDate(currRecord.getDate());
                i++;
            }
            optionWeeks.add(optionWeek);
        }
        optionWeekRepository.saveAll(optionWeeks);
    }

    private LocalDate getNextFriday(LocalDate date) {
        while (true) {
            date = date.plusDays(1);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) return date;
        }
    }
}