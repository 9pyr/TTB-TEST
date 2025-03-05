import { saveSurvey } from "@/core/apis"
import { useMutation } from "@tanstack/react-query"

const useSaveSurvey = () => {
  return useMutation({
    mutationFn: saveSurvey,
  })
}

export default useSaveSurvey
