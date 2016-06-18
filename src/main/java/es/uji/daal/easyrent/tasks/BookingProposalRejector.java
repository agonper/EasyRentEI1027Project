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
public class BookingProposalRejector {

    @Autowired
    private BookingProposalRepository proposalRepository;

    @Autowired
    private AvailabilityPeriodRepository periodRepository;

    @Scheduled(cron = "01 0 0 * * *")
    public void rejectInfeasibleProposals() {
        List<BookingProposal> proposals = proposalRepository.findInfeasibleProposalsFrom(
                DateUtils.getDateMinusDays(new Date(), 2));

        Logger.getAnonymousLogger().info(String.format("Rejecting %d proposals because of its infeasibility",
                proposals.size()));

        List<AvailabilityPeriod> periods = proposals.stream().map(proposal -> {
            proposal.reject();

            AvailabilityPeriod period = proposal.getProperty().createAvailabilityPeriod();
            period.setStartDate(proposal.getStartDate());
            period.setEndDate(proposal.getEndDate());
            return period;
        }).collect(Collectors.toList());

        // TODO send email to affected tenants

        proposalRepository.save(proposals);
        periodRepository.save(periods);
    }
}
