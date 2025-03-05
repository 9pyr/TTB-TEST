export interface Choice {
  title: string
  value: string
}

export interface Setting {
  key: string
  value: string
}

export interface SurveyInput {
  type: "rank" | "radio" | "text"
  title: string
  settings: Setting[]
  choices?: Choice[]
}

export interface SurveyModel {
  id: string
  serviceSatisfaction: string
  improvementSuggestion: string
  feedbackComments?: string
}
