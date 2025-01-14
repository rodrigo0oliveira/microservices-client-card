package com.microservices.mscreditvalidator.domain.dto;

import com.microservices.mscreditvalidator.domain.ApprovedCard;

import java.util.List;

public record ResponseEvaluation(List<ApprovedCard> approvedCards) {
}
