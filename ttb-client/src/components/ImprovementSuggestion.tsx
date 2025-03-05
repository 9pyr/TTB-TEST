import { Choice } from "@/core/apis/types"
import { Button, Form, FormInstance, Radio, RadioChangeEvent } from "antd"
import { useWatch } from "antd/es/form/Form"
import clsx from "clsx"

interface ImprovementSuggestionProps {
  form: FormInstance
  title?: string
  choices?: Choice[]
  settings?: Record<string, string>
}

const ImprovementSuggestion = ({
  form,
  title,
  choices,
  settings = {},
}: ImprovementSuggestionProps) => {
  const { require } = settings

  const isRequired = require === "true"

  const value = useWatch("improvementSuggestion", form)

  const handleChangeValue = (e: RadioChangeEvent) => {
    form.setFieldsValue({
      improvementSuggestion: e.target.value,
    })
  }

  return (
    <Form.Item
      name="improvementSuggestion"
      rules={[{ required: isRequired, message: "โปรดระบุ" }]}
      className="w-full"
    >
      <div className="grid grid-flow-row gap-4">
        <div className="font-bold">{title}</div>

        <Radio.Group value={value} onChange={handleChangeValue}>
          <div className="grid grid-flow-row gap-2">
            {choices?.map(({ title, value: choiceValue }) => (
              <Button
                key={title}
                className={clsx("!px-0 !h-fit", {
                  "!border-[#f68b1f]": choiceValue === value,
                })}
              >
                <Radio className="w-full !px-4 !py-3" value={choiceValue}>
                  {title}
                </Radio>
              </Button>
            ))}
          </div>
        </Radio.Group>
      </div>
    </Form.Item>
  )
}

export default ImprovementSuggestion
