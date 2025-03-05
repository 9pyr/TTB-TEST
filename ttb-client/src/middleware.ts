import { NextURL } from "next/dist/server/web/next-url"
import { NextRequest, NextResponse } from "next/server"

export function middleware(req: NextRequest) {
  const { pathname, origin } = req.nextUrl

  if (["/"].includes(pathname)) {
    const surveyUrl = new NextURL("/survey-app", origin)
    return NextResponse.redirect(surveyUrl)
  }

  return NextResponse.next()
}

export const config = {
  matcher: ["/", "/survey-app"],
}
