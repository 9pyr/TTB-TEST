import { Layout as BaseLayout, Form, FormProps } from "antd"
import { Content, Footer, Header } from "antd/es/layout/layout"
import Image from "next/image"
import Link from "next/link"
import React from "react"

const Layout = ({
  children,
  footer,
  ...props
}: React.PropsWithChildren<{ footer?: React.ReactNode }> & FormProps) => {
  return (
    <Form {...props} className="flex justify-center">
      <BaseLayout className="h-[100vh] max-w-2xl">
        <Header>
          <div className="flex">
            <Link href="/" className="py-4">
              <Image
                src="/logo-default.png"
                alt="ttb-logo"
                width={50}
                height={24}
              />
            </Link>
          </div>
        </Header>
        <Content className="flex justify-center px-4">{children}</Content>
        <Footer className="flex justify-center">{footer}</Footer>
      </BaseLayout>
    </Form>
  )
}

export default Layout
