import { Form, FormInstance } from "antd"
import { useWatch } from "antd/es/form/Form"
import TextArea from "antd/es/input/TextArea"
import { ChangeEvent } from "react"

interface FeedbackCommentsProps {
  form: FormInstance
  title?: string
  settings?: Record<string, string>
}

const FeedbackComments = ({
  form,
  title,
  settings = {},
}: FeedbackCommentsProps) => {
  const { max_length, require } = settings

  const isRequired = require === "true"

  const value = useWatch("feedbackComments", form)

  const handleChangeValue = (e: ChangeEvent<HTMLTextAreaElement>) => {
    form.setFieldsValue({
      feedbackComments: e.target.value,
    })
  }

  return (
    <Form.Item
      name="feedbackComments"
      rules={[{ required: isRequired, message: "โปรดระบุ" }]}
      className="w-full"
    >
      <div className="grid grid-flow-row gap-4">
        <div className="font-bold">
          {title} {!isRequired && "(ถ้ามี)"}
        </div>
        <TextArea
          value={value}
          onChange={handleChangeValue}
          rows={4}
          placeholder="ความคิดเห็นเพิ่มเติม"
          maxLength={max_length ? Number(max_length) : undefined}
        />
      </div>
    </Form.Item>
  )
}

export default FeedbackComments
