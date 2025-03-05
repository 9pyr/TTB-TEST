import { getSurveyInputByType } from "@/core/apis"
import { Step } from "@/core/constants"
import { useQuery } from "@tanstack/react-query"

const useGetSurveyInput = (step: Step) => {
  return useQuery({
    queryKey: ["input", step],
    queryFn: () => getSurveyInputByType(step),
  })
}

export default useGetSurveyInput
