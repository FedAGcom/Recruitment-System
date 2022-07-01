package com.fedag.recruitmentSystem.jira;

import com.fedag.recruitmentSystem.enums.IssueStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class IssueDto {
    private String issueName;
    private String projectName;
    private IssueStatus issueStatus;
}