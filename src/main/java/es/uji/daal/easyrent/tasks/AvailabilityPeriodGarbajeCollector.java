package es.uji.daal.easyrent.tasks;

import es.uji.daal.easyrent.model.AvailabilityPeriod;
import es.uji.daal.easyrent.model.BookingProposal;
import es.uji.daal.easyrent.repository.AvailabilityPeriodRepository;
import es.uji.daal.easyrent.repository.BookingProposalRepository;
import es.uji.daal.easyrent.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Alberto on 17/06/2016.
 */
@Component
public class AvailabilityPeriodGarbajeCollector {

    @Autowired
    private AvailabilityPeriodRepository periodRepository;

    @Scheduled(cron = "00 10 0 * * *")
    public void rejectInfeasibleProposals() {
        int removeCount = periodRepository.removeOutdatedPeriods();

        Logger.getAnonymousLogger().info(String.format("Removed %d outdated availability periods", removeCount));
    }
}
